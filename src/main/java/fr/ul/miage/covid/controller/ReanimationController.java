package fr.ul.miage.covid.controller;

import fr.ul.miage.covid.App;
import fr.ul.miage.covid.model.Hospitals;
import fr.ul.miage.covid.model.Reanimations;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class ReanimationController extends PanelController{

	@Override
	public void init() {
		this.datas=App.loader_drh.getReanimations();
	}
}
