package fr.ul.miage.covid.model;

public class Vaccinations extends Indicators<Vaccination>{

	public void add(Vaccination v) {
		if (!this.deaths.contains(v)) {
			this.deaths.add(v);
		}
	}
}
