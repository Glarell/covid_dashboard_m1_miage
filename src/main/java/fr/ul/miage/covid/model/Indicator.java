package fr.ul.miage.covid.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Indicator {

	private Date date;
	
	private Long amount;
	
	private String code;
	
	public Indicator(String date, Long amount, String code) {
		try {
			this.date = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			this.amount = amount;
			this.code=code;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Date getDate() {
		return date;
	}

	public String getStringDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		return formatter.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Indicator [date=" + date + ", amount=" + amount + "]";
	}
	
}
