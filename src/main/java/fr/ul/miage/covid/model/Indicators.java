package fr.ul.miage.covid.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class Indicators<T> {

ArrayList<T> deaths = new ArrayList<T>();
	
	public Indicators() {
	}
	
	public void add(T death) {
		this.deaths.add(death);
	}
	
	public ArrayList<T> getIndicatorByDate(String date) {
		return (ArrayList<T>) this.deaths
				  .stream()
				  .filter(death -> ((Indicator) death).getStringDate().equals(date))
				  .collect(Collectors.toList());
	}
	
	public ArrayList<T> getIndicatorBetweenDates( Date dateFrom, Date dateTo) {
		try {
			if (dateFrom.after(dateTo)) { throw new ParseException("Erreur : Date de début supérieure à la date de fin",0); }
			return (ArrayList<T>) this.deaths
					  .stream()
					  .filter(death -> ((Indicator) death).getDate().after(dateFrom) && ((Indicator) death).getDate().before(dateTo) )
					  .collect(Collectors.toList());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<T>();
	}
	
	public ArrayList<T> getAllIndicators() {
		return this.deaths;
	}
	
	public int getMaxAllIndicators() {
		return 0;
	}
	
	public ArrayList<T> getIndicatorByDepartement(String dep) {
		String departement="DEP-".concat(dep);
		return (ArrayList<T>) this.deaths
				  .stream()
				  .filter(death -> ((Indicator) death).getCode().equals(departement) || dep.contains("*"))
				  .collect(Collectors.toList());
	}
	
	public ArrayList<String> getListOfDepartement() {
		ArrayList<String> deps = new ArrayList<String>();
		this.deaths
		.forEach(death -> {
			if (!deps.contains(((Indicator) death).getCode().replace("DEP-", "")) && ((Indicator) death).getCode().startsWith("DEP-")) {
				deps.add(((Indicator) death).getCode().replace("DEP-", ""));
			}
		});
		return (ArrayList<String>) deps.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Indicators [toString()=" + super.toString() + "]";
	}

}
