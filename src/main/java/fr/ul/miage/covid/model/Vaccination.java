package fr.ul.miage.covid.model;

public class Vaccination extends Indicator {

	public Vaccination(String date, Long amount, String code) {
		super(date, amount, code);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object o) {
		return this.getDate().equals(((Indicator) o).getDate()) && this.getCode().equals(((Indicator) o).getCode());
	}
}
