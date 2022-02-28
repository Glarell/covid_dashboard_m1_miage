package fr.ul.miage.covid.model;

import java.util.Date;

public class Hospital extends Indicator{

	public Hospital(String date, Long amount, String code) {
		super(date, amount, code);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Hospital : [date="+this.getDate()+", amount="+this.getAmount()+"]";
	}
}
