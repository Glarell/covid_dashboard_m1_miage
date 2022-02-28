package fr.ul.miage.covid.model;

public class Death extends Indicator{

	public Death(String date, Long amount, String code) {
		super(date, amount, code);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Death : [date="+this.getDate()+", amount="+this.getAmount()+"]";
	}
}
