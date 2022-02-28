package fr.ul.miage.covid;

import java.io.IOException;

import fr.ul.miage.covid.model.Loader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {
	
	public final static Loader loader = new Loader("france.csv");

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Covid Dashboard");
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/main.fxml"));
			TabPane mainpage = loader.load();
			Scene scene = new Scene(mainpage);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
