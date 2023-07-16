package it.polito.ezshop.model;

public class ReturnTransaction extends BalanceOperation{

	private String status;
    private String productCode;
    private Integer quantity;
    private Integer saleTransactionID;

    public ReturnTransaction(Integer saleTransactionID,String type, Double amount){
        super(type, amount);
        this.saleTransactionID = saleTransactionID;
        this.status = "OPENED";
        this.quantity = 0;
    }
    
    //Constructor for DB
	public ReturnTransaction(Integer returnID, Double amount, String date, String status, String productCode, Integer quantity, Integer transactionID) {
		super(returnID, "RETURN", amount, date);
		this.status = status;
		this.productCode = productCode;
		this.quantity = quantity;
		this.saleTransactionID = transactionID;
	}
	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSaleTransactionID() {
		return saleTransactionID;
	}

	public void setSaleTransactionID(Integer saleTransactionID) {
		this.saleTransactionID = saleTransactionID;
	}
}
