package fr.ul.miage.covid.controller;

import java.io.IOException;
import java.util.Collections;

import org.json.simple.parser.ParseException;

import fr.ul.miage.covid.App;
import fr.ul.miage.covid.model.Deaths;
import fr.ul.miage.covid.model.Loader;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class DeathController extends PanelController<Deaths> {
	
	@Override
	public void init() {
		this.datas=App.loader.getDeaths();
	}
}
