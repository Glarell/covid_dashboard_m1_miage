package fr.ul.miage.covid.view;
import fr.ul.miage.covid.model.Death;
import fr.ul.miage.covid.model.Indicator;
import fr.ul.miage.covid.model.Indicators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableRecap extends TableView<Indicator>{
	
	Indicators<Indicator> listOfIndicator = new Indicators<Indicator>();

	public TableRecap(Indicators listOfIndicator) {
		this.listOfIndicator=listOfIndicator;
		 TableColumn<Indicator, String> date = new TableColumn<Indicator, String>("Date");
		 TableColumn<Indicator, String> amount = new TableColumn<Indicator, String>("Amount");
		 TableColumn<Indicator, String> code= new TableColumn<Indicator, String>("Code");
		 date.setCellValueFactory(new PropertyValueFactory<>("date"));
		 amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		 code.setCellValueFactory(new PropertyValueFactory<>("code"));
		 ObservableList<Indicator> list = getIndicators();
	     this.setItems(list);
	     this.getColumns().addAll(date, amount, code);
	}
	
	private ObservableList<Indicator> getIndicators() {
		return FXCollections.observableArrayList(this.listOfIndicator.getAllIndicators());
	}
}
