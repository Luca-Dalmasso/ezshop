package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.*;


public class SaleTransaction extends BalanceOperation implements it.polito.ezshop.data.SaleTransaction {

	private static Integer ticketNumberCounter = 0;
	
	private Integer ticketNumber;
	private List<TicketEntry> entries;
	private String state;
	private String paymentType;
	private Double discount;
	
	
	
	public SaleTransaction() {
		super("SALE", 0.0);
		SaleTransaction.ticketNumberCounter++;
		this.ticketNumber = SaleTransaction.ticketNumberCounter;
		this.entries = new ArrayList<TicketEntry>();
		this.state = "OPENED";
		this.paymentType = null;
		this.discount = 0.0;
	}
	
	//Constructor for DB
	public SaleTransaction(Integer saleID, Double amount, String date, Integer ticketNumber, String entries, String state,
			String paymentType, Double discount) {
		super(saleID, "SALE", amount, date);
		this.ticketNumber = ticketNumber;
		if(SaleTransaction.ticketNumberCounter < ticketNumber)
			SaleTransaction.ticketNumberCounter = ticketNumber;
		this.entries = setEntriesToList(entries);
		this.state = state;
		this.paymentType = paymentType;
		this.discount = discount;
	}

	private List<TicketEntry> setEntriesToList(String entries) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return Arrays.stream(entries.split(";")).map(s -> gson.fromJson(s, TicketEntry.class)).collect(Collectors.toCollection(ArrayList::new));
	}
	
	public String getEntriesToString () {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return this.entries.stream().map(t -> gson.toJson(t)).collect(Collectors.joining(";"));
	}

	public void addProduct(String productCode, String description, Double sellPrice, Integer quantity) {
		TicketEntry p = entries.stream().filter(entry -> entry.getBarCode().equals(productCode)).findAny().orElse(null);
		if(p == null) {
			p = new TicketEntry(productCode, description, quantity, sellPrice);
			this.entries.add(p);
		}
		else
			p.setAmount(p.getAmount() + quantity);
		this.amount += sellPrice * quantity * (1.0 - p.getDiscountRate()) * (1.0 - this.discount);
	}
	
	public boolean deleteProduct(String productCode, Integer quantity) {
		TicketEntry p = entries.stream().filter(entry -> entry.getBarCode().equals(productCode)).findAny().orElse(null);
		if(p == null || p.getAmount() < quantity)
			return false;
		this.amount -= p.getPricePerUnit() * quantity * (1.0 - p.getDiscountRate()) * (1.0 - this.discount);
		if(p.getAmount() > quantity)
			p.setAmount(p.getAmount() - quantity);
		else
			entries.remove(p);	
		return true;
	}
	
	public boolean setDiscountToProduct(String productCode, Double discount) {
		TicketEntry p = entries.stream().filter(entry -> entry.getBarCode().equals(productCode)).findAny().orElse(null);
		if(p == null)
			return false;
		p.setDiscountRate(discount);
		Double disc = p.getAmount() * p.getPricePerUnit() * discount;
		if(this.discount != 0)
			this.amount -= disc * this.discount;
		else
			this.amount -= disc;
		return true;
	}

	@Override
	public Integer getTicketNumber() {
		return this.ticketNumber;
	}

	@Override
	public void setTicketNumber(Integer ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	@Override
	public List<it.polito.ezshop.data.TicketEntry> getEntries() {
		List<it.polito.ezshop.data.TicketEntry> lst = this.entries.stream().collect(Collectors.toList());
		return lst;
	}

	@Override
	public void setEntries(List<it.polito.ezshop.data.TicketEntry> entries) {
		List<TicketEntry> lst = entries.stream().map(TicketEntry.class::cast).collect(Collectors.toCollection(ArrayList::new));
		this.entries = lst;
	}

	@Override
	public double getDiscountRate() {
		return this.discount;
	}

	@Override
	public void setDiscountRate(double discountRate) {
		this.amount = this.amount / (1.0 - this.discount); 
		this.discount = discountRate;
		this.amount -= this.amount * discountRate; 
	}

	@Override
	public double getPrice() {
		return this.amount;
	}

	@Override
	public void setPrice(double price) {
		this.amount = price;	
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public static void setTicketNumberCounter(Integer ticketID) {
		SaleTransaction.ticketNumberCounter = ticketID;
	}
	
}
