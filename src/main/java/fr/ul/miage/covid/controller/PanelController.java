package fr.ul.miage.covid.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import fr.ul.miage.covid.model.Indicator;
import fr.ul.miage.covid.model.Indicators;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class PanelController<T> {

	@FXML private CategoryAxis xAxis;
	@FXML private NumberAxis yAxis;
	@FXML private LineChart<String, Number> linechart;
	@FXML private ComboBox combobox_dep;
	@FXML private DatePicker datePickerBegin;
	@FXML private DatePicker datePickerEnd;
	@FXML private Button delete_filters;
	@FXML private Button apply_filters;
	@FXML private Button btn_open;
	@FXML private Button btn_close;
	ZoneId defaultZoneId = ZoneId.systemDefault();

	protected T datas;
	
	public void init() {
		//change loads datas
	}
	 
	@FXML
	private void initialize() {
		init();
		btn_close.setVisible(false);
		combobox_dep.setVisible(false);
		datePickerBegin.setVisible(false);
		datePickerEnd.setVisible(false);
		delete_filters.setVisible(false);
		apply_filters.setVisible(false);
		xAxis.setLabel("Date");
		yAxis.setLabel("Amount");
		initDepartementSelector();
		
	}
	 
	 public void onDepChoosen() throws ParseException {
		String combo_value = (String) combobox_dep.getValue();
		XYChart.Series<String,Number> serie = new XYChart.Series<String,Number>();
		serie.setName(combo_value);
		if (combo_value == null) { combo_value="*"; }
		for (XYChart.Series<String,Number> tmp : this.linechart.getData()) {
			if (tmp.getName() == combo_value) {
				this.linechart.getData().remove(tmp);
			}
		}
		((Indicators<Indicator>) datas).getIndicatorByDepartement(combo_value)
		.forEach(death -> serie.getData().add(new XYChart.Data<>(death.getStringDate(), death.getAmount())));
		linechart.getData().add(serie);
		if (this.datePickerBegin.getValue() != null && this.datePickerEnd.getValue() != null) {
			this.apply_filters();
		}
	 }
		
		public void initDepartementSelector() {
			this.combobox_dep.getItems().addAll(((Indicators<Indicator>) datas).getListOfDepartement());
		}
		
		public void delete_filters() {
			this.linechart.getData().clear();
			this.datePickerBegin.setValue(null);
			this.datePickerEnd.setValue(null);
		}
		
		public void open_filters() {
			btn_open.setVisible(false);
			btn_close.setVisible(true);
			combobox_dep.setVisible(true);
			datePickerBegin.setVisible(true);
			datePickerEnd.setVisible(true);
			delete_filters.setVisible(true);
			apply_filters.setVisible(true);
		}
		
		public void close_filters() {
			btn_open.setVisible(true);
			btn_close.setVisible(false);
			combobox_dep.setVisible(false);
			datePickerBegin.setVisible(false);
			datePickerEnd.setVisible(false);
			delete_filters.setVisible(false);
			apply_filters.setVisible(false);
		}
		
		public void apply_filters() throws ParseException {
			Date datepickerbegin = Date.from(this.datePickerBegin.getValue().atStartOfDay(defaultZoneId).toInstant());
			Date datepickerend = Date.from(this.datePickerEnd.getValue().atStartOfDay(defaultZoneId).toInstant());
			//linechart.getData().clear();
			parse_series(datepickerbegin, datepickerend);
		}
	
		public void apply_filters(Date dateD, Date dateF) throws ParseException {
			//linechart.getData().clear();
			parse_series(dateD, dateF);
		}
		
		public void parse_series(Date datepickerbegin,Date datepickerend) {
			ArrayList<XYChart.Series<String,Number>> list_tmp = new ArrayList<XYChart.Series<String,Number>>();
			for (Series<String, Number> serie : this.linechart.getData()) {
				XYChart.Series<String,Number> tmp = new XYChart.Series<String,Number>();
				tmp.setName(serie.getName());
				((Indicators<Indicator>) datas).getIndicatorByDepartement(serie.getName())
				.stream()
				.filter(d -> {
					try {
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d.getStringDate());
						return date.after(datepickerbegin) && date.before(datepickerend);
					} catch (ParseException e) {
						return false;
					}
				})
				.forEach(death -> tmp.getData().add(new XYChart.Data<>(death.getStringDate(), death.getAmount())));
				list_tmp.add(tmp);
			}
			this.linechart.getData().clear();
			this.linechart.getData().addAll(list_tmp);
		}

		public CategoryAxis getxAxis() {
			return xAxis;
		}

		public NumberAxis getyAxis() {
			return yAxis;
		}

		public LineChart<String, Number> getLinechart() {
			return linechart;
		}

		public ComboBox getCombobox_dep() {
			return combobox_dep;
		}

		public DatePicker getDatePickerBegin() {
			return datePickerBegin;
		}

		public DatePicker getDatePickerEnd() {
			return datePickerEnd;
		}

		public Button getDelete_filters() {
			return delete_filters;
		}

		public ZoneId getDefaultZoneId() {
			return defaultZoneId;
		}

		public T getDatas() {
			return datas;
		}
		
}
