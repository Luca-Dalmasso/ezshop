package it.polito.ezshop.model;


public class CreditCard {

    private String creditCardCode;
    private Double balance;

    public CreditCard (String code, Double balance){
           this.creditCardCode = code;
           this.balance = balance;
    }

	public String getCreditCardCode() {
		return this.creditCardCode;
	}

    public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
