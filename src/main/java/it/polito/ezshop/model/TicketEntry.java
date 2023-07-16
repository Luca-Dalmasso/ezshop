package it.polito.ezshop.model;

public class TicketEntry implements it.polito.ezshop.data.TicketEntry {

	private String barCode;
	private String description;
	private Integer quantity;
	private Double price;
	private Double discount;
	
	
	
	public TicketEntry(String barCode, String description, Integer quantity, Double price) {
		this.barCode = barCode;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.discount = 0.0;
	}
	
	@Override
	public String getBarCode() {
		return this.barCode;
	}

	@Override
	public void setBarCode(String barCode) {
		this.barCode = barCode;	
	}

	@Override
	public String getProductDescription() {
		return this.description;
	}

	@Override
	public void setProductDescription(String productDescription) {
		this.description = productDescription;
		
	}

	@Override
	public int getAmount() {
		return this.quantity;
	}

	@Override
	public void setAmount(int amount) {
		this.quantity = amount;
	}

	@Override
	public double getPricePerUnit() {
		return this.price;
	}

	@Override
	public void setPricePerUnit(double pricePerUnit) {
		this.price = pricePerUnit;
	}

	@Override
	public double getDiscountRate() {
		return this.discount;
	}

	@Override
	public void setDiscountRate(double discountRate) {
		this.discount = discountRate;	
	}
	
	

}
