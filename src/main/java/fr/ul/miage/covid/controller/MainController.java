package fr.ul.miage.covid.controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;

import fr.ul.miage.covid.model.Deaths;
import fr.ul.miage.covid.model.Hospitals;
import fr.ul.miage.covid.App;
import fr.ul.miage.covid.model.Death;
import fr.ul.miage.covid.model.Indicator;
import fr.ul.miage.covid.model.Indicators;
import fr.ul.miage.covid.view.TableRecap;
import fr.ul.miage.covid.model.Reanimations;
import fr.ul.miage.covid.model.Vaccinations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MainController {
	
	@FXML private GridPane gridpane;

	@FXML private ComboBox combobox_dep;
	@FXML private DatePicker datePickerBegin;
	@FXML private DatePicker datePickerEnd;
	@FXML private Button delete_filters;
	@FXML private Button apply_filters;
	
	@FXML private Label resume_dea;
	@FXML private Label resume_hos;
	@FXML private Label resume_vac;
	@FXML private Label resume_rea;
	
	@FXML private BorderPane borderpane_dea;
	@FXML private BorderPane borderpane_hos;
	@FXML private BorderPane borderpane_rea;
	@FXML private BorderPane borderpane_vac;
	
	ZoneId defaultZoneId = ZoneId.systemDefault();

	PanelController<Deaths> death_c;
	PanelController<Hospitals> hospital_c;
	PanelController<Reanimations> reanimation_c;
	PanelController<Vaccinations> vaccination_c;
	
	@FXML
	private void initialize() {
		try {
			FXMLLoader loader = new FXMLLoader();
			//death panel
			loader.setLocation(getClass().getResource("/death_panel.fxml"));
			BorderPane death_panel = loader.load();
			death_c = loader.getController();
			//hospital panel
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/hospital_panel.fxml"));
			BorderPane hospital_panel = loader.load();
			hospital_c = loader.getController();
			//reanimation panel
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/reanimation_panel.fxml"));
			BorderPane reanimation_panel = loader.load();
			reanimation_c = loader.getController();
			//vaccination panel
			loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/vaccination_panel.fxml"));
			BorderPane vaccination_panel = loader.load();
			vaccination_c = loader.getController();
			this.gridpane.add(death_panel,0,0);
			this.gridpane.add(hospital_panel,1,0);
			this.gridpane.add(reanimation_panel,0,1);
			this.gridpane.add(vaccination_panel,1,1);
			initDepartementSelector();
			borderpane_dea.setCenter(new TableRecap(App.loader_drh.getDeaths()));
			borderpane_hos.setCenter(new TableRecap(App.loader_drh.getHospitals()));
			borderpane_rea.setCenter(new TableRecap(App.loader_drh.getReanimations()));
			borderpane_vac.setCenter(new TableRecap(App.loader_v.getVaccinations()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onDepChoosen() throws ParseException {
		String combo_value = (String) combobox_dep.getValue();
		if (combo_value == null) { combo_value="*"; }
		change_panel_by_controller(death_c,combo_value);
		change_panel_by_controller(hospital_c,combo_value);
		change_panel_by_controller(reanimation_c,combo_value);
		change_panel_by_controller(vaccination_c,combo_value);
	}

	public void change_panel_by_controller(PanelController ctrl, String combo_value) throws ParseException {
		XYChart.Series<String,Number> serie = new XYChart.Series<String,Number>();
		serie.setName(combo_value);
		for (XYChart.Series<String,Number> tmp : ((LineChart<String, Number>) ctrl.getLinechart()).getData() ){
			if (tmp.getName() == combo_value) {
				ctrl.getLinechart().getData().remove(tmp);
			}
		}
		((Indicators<Indicator>) ctrl.getDatas()).getIndicatorByDepartement(combo_value)
		.forEach(death -> serie.getData().add(new XYChart.Data<>(death.getStringDate(), death.getAmount())));
		ctrl.getLinechart().getData().add(serie);
		if (this.datePickerBegin.getValue() != null && this.datePickerEnd.getValue() != null) {
			this.apply_filters();
		}
	}
	
	public void apply_filters() throws ParseException {
		Date datepickerbegin = Date.from(this.datePickerBegin.getValue().atStartOfDay(defaultZoneId).toInstant());
		Date datepickerend = Date.from(this.datePickerEnd.getValue().atStartOfDay(defaultZoneId).toInstant());
		death_c.apply_filters(datepickerbegin,datepickerend);
		hospital_c.apply_filters(datepickerbegin,datepickerend);
		reanimation_c.apply_filters(datepickerbegin,datepickerend);
		vaccination_c.apply_filters(datepickerbegin,datepickerend);
	}
	
	public void delete_filters() {
		death_c.delete_filters();
		hospital_c.delete_filters();
		reanimation_c.delete_filters();
		vaccination_c.delete_filters();
		this.datePickerBegin.setValue(null);
		this.datePickerEnd.setValue(null);
	}
	
	public void initDepartementSelector() {
		this.combobox_dep.getItems().addAll(((Deaths) death_c.getDatas()).getListOfDepartement());
	}
}
