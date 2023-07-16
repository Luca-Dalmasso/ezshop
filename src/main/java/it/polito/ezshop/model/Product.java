package it.polito.ezshop.model;

public class Product {
	
	private String rfID = null;
	private String status = null;
	private Integer pID = -1;
	private Integer saleID = -1;
	
	public Product(String rfID, Integer pID, Integer saleID) {
		this.rfID = rfID;
		this.status = "AVAILABLE";
		this.pID = pID;
		this.saleID = saleID;
	}
	
	//Constructor for DB
	public Product(String rfID, Integer pID, String status, Integer saleID) {
		this.rfID = rfID;
		this.status = status;
		this.pID = pID;
		this.saleID = saleID;
	}

	public String getRfID() {
		return this.rfID;
	}

	public void setRfID(String rfID) {
		this.rfID = rfID;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getpID() {
		return this.pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public Integer getSaleID() {
		return this.saleID;
	}

	public void setSaleID(Integer saleID) {
		this.saleID = saleID;
	}
	
}
