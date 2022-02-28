package fr.ul.miage.covid.model;

import java.util.Date;

import com.opencsv.bean.CsvBindByPosition;

public class Vaccination extends Indicator {
    
	public Vaccination(String date, Long amount, String code) {
		super(date, amount, code);
		// TODO Auto-generated constructor stub
	}

	//@Override
	//public boolean equals(Object o) {
	//	return this.getDate().equals(((Indicator) o).getDate()) && this.getCode().equals(((Indicator) o).getCode());
	//}
	
	@Override
	public String toString() {
		return "Vaccination : [date="+this.getDate()+", amount="+this.getAmount()+"]";
	}
}
