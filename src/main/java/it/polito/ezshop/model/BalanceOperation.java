package it.polito.ezshop.model;

import java.time.LocalDate;

public class BalanceOperation implements it.polito.ezshop.data.BalanceOperation {

	private static Integer idCounter = 0;
	protected Integer transactionID;
	protected String type;
	protected Double amount;
	protected LocalDate date;
	
	public BalanceOperation(String type, Double amount) {
		BalanceOperation.idCounter++;
		this.transactionID = BalanceOperation.idCounter;
		this.type = type;
		this.amount = amount;
		this.date = LocalDate.now();
	}
	
	//Constructor for DB
	public BalanceOperation(Integer transactionID, String type, Double amount, String date) {
		this.transactionID = transactionID;
		this.type = type;
		this.amount = amount;
		this.date = LocalDate.parse(date);
	}

	@Override
	public int getBalanceId() {
		return transactionID;
	}

	@Override
	public void setBalanceId(int balanceId) {
		this.transactionID = balanceId;
		
	}

	@Override
	public LocalDate getDate() {
		return this.date;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
		
	}

	@Override
	public double getMoney() {
		return this.amount;
	}

	@Override
	public void setMoney(double money) {
		this.amount = money;
		
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
		
	}
	
	public static void setIdCounter(Integer maxID) {
		BalanceOperation.idCounter = maxID;
	}
}


