package fr.ul.miage.covid.model;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Loader extends JSONParser {
	
	private FileReader fileReader;
	
	public Deaths deaths = new Deaths();
	
	public Reanimations reanimations = new Reanimations();
	
	public Hospitals hospitals = new Hospitals();
	
	public Vaccinations vaccinations= new Vaccinations();
	
	public Loader(String filename, int type) {
		try {
			ClassLoader classLoader = Loader.class.getClassLoader();
			URL resource = classLoader.getResource(filename);
			this.fileReader=(new FileReader(resource.getFile()));
			if (type == 0) {
				initDRH();
			} else {
				initV();
			}
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initDRH() throws FileNotFoundException, IOException, ParseException {
		JSONArray json = (JSONArray) new JSONParser().parse(this.fileReader);
		Iterator<JSONObject> iterator = json.iterator();
        while (iterator.hasNext()) {
        	JSONObject obj = iterator.next();
        	String date=(String)obj.get("date");
        	Long death=(Long)obj.get("deces");
        	Long hospitalization=(Long)obj.get("hospitalises");
        	Long reanimation=(Long)obj.get("reanimation");
        	String code=(String)obj.get("code");
        	if (!code.contains("DEP-")) { continue; }
        	//init DEATHS
	        if (death != null && date != null && code != null) {
		       	deaths.add(new Death(date,death,code));
	        }
	        //init Reanimations
	        if (reanimation != null && date != null && code != null) {
		       	reanimations.add(new Reanimation(date,reanimation,code));
	        }
	        //init Hospitals
	        if (hospitalization != null && date != null && code != null) {
		       	hospitals.add(new Hospital(date,hospitalization,code));
	        }
        }
	}
	
	public void initV() {
		try {
			CSVParser p = this.builderCVSParser();
			for (CSVRecord r : p) {
				String dep = r.get(0);
				String date = r.get(2);
				Long n_cum_dose1 = Long.valueOf(r.get(8));
				dep="DEP-".concat(dep);
				vaccinations.add(new Vaccination(date, n_cum_dose1, dep));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public CSVParser builderCVSParser() throws IOException {
		CSVFormat csvf = CSVFormat.DEFAULT.withCommentMarker('#').withDelimiter(';');
		return new CSVParser(this.fileReader,csvf);
	}

	public FileReader getFileReader() {
		return fileReader;
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
