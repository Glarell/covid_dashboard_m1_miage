package fr.ul.miage.covid.model;

public class Reanimation extends Indicator{

	public Reanimation(String date, Long amount, String code) {
		super(date, amount, code);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Reanimation : [date="+this.getDate()+", amount="+this.getAmount()+"]";
	}
}
