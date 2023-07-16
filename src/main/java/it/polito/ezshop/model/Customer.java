package it.polito.ezshop.model;

public class Customer implements it.polito.ezshop.data.Customer {

    private Integer customerID;
    private String name;
    private String customerCard;
    private Integer points;

    public Customer(Integer customerID, String name) {
        this.customerID = customerID;
        this.name = name;
        this.customerCard = null;
        this.points = 0;
    }
    
    //Constructor for DB
    public Customer(Integer customerID, String name, String customerCard, Integer points) {
        this.customerID = customerID;
        this.name = name;
        this.customerCard = customerCard;
        this.points = points;
    }

	@Override
	public String getCustomerName() {
		return this.name;
	}

	@Override
	public void setCustomerName(String customerName) {
		this.name = customerName;
		
	}

	@Override
	public String getCustomerCard() {
		return this.customerCard;
	}

	@Override
	public void setCustomerCard(String customerCard) {
		this.customerCard = customerCard;
	}

	@Override
	public Integer getId() {
		return this.customerID;
	}

	@Override
	public void setId(Integer id) {
		this.customerID = id;
	}

	@Override
	public Integer getPoints() {
		return this.points;
	}

	@Override
	public void setPoints(Integer points) {
		this.points = points;
		
	}

}