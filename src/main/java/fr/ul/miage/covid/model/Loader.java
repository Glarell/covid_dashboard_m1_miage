package fr.ul.miage.covid.model;

import java.io.FileNotFoundException;


import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class Loader extends JSONParser {
	
	public Deaths deaths = new Deaths();
	
	public Reanimations reanimations = new Reanimations();
	
	public Hospitals hospitals = new Hospitals();
	
	public Vaccinations vaccinations= new Vaccinations();
	
	public FileReader fileReader;
	
	public static void main(String[] args) {
		Loader loader_v = new Loader("france.csv");
	}
	
	public Loader(String filename) {
		try {
			ClassLoader classLoader = Loader.class.getClassLoader();
			URL resource = classLoader.getResource(filename);
			this.fileReader=(new FileReader(resource.getFile()));
			init();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init() {
		try (CSVReader reader = new CSVReader(this.fileReader)) {
            List<String[]> r = reader.readAll();
            for (String[] item : r) {
            	String date = item[0];
            	String code = item[2];
            	Long amount_dea = getProperLongFormat(item[4]);
            	Long amount_vac = getProperLongFormat(item[9]);
            	Long amount_rea = getProperLongFormat(item[5]);
            	Long amount_hos = getProperLongFormat(item[6]);
            	this.deaths.add(new Death(date,amount_dea,code));
            	this.vaccinations.add(new Vaccination(date,amount_vac,code));
            	this.reanimations.add(new Reanimation(date,amount_rea,code));
            	this.hospitals.add(new Hospital(date,amount_hos,code));
            }
        } catch (IOException | CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Long getProperLongFormat(String s) {
		if (s.isEmpty()) {
			return Long.valueOf(0);
		} else {
			return Long.valueOf(s);
		}
	}

	public Deaths getDeaths() {
		return deaths;
	}

	public Reanimations getReanimations() {
		return reanimations;
	}

	public Hospitals getHospitals() {
		return hospitals;
	}

	public Vaccinations getVaccinations() {
		return vaccinations;
	}

}
