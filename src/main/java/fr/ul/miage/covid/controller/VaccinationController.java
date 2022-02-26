package fr.ul.miage.covid.controller;

import fr.ul.miage.covid.App;
import fr.ul.miage.covid.model.Indicators;
import fr.ul.miage.covid.model.Reanimation;
import fr.ul.miage.covid.model.Reanimations;
import fr.ul.miage.covid.model.Vaccinations;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class VaccinationController extends PanelController{

	@Override
	public void init() {
		this.datas=App.loader_v.getVaccinations();
	}
}
