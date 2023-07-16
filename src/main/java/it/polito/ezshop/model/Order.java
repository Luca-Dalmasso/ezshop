package it.polito.ezshop.model;


public class Order extends BalanceOperation implements it.polito.ezshop.data.Order {
	private static Integer OrderidCounter = 0;
	private String productCode;
	private Double pricePerUnit ;
	private int quantity;
	private String status;
	private Integer orderId;
	
	public Order(String productCode, Double pricePerUnit ,int quantity, String status) {
		super("ORDER", pricePerUnit * quantity);
		this.productCode = productCode;
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
		this.status = status;
		Order.OrderidCounter++;
		this.orderId = Order.OrderidCounter;
	}
	
	//Constructor for DB
	public Order(Integer transactionID, Double amount, String date, String productCode, Double pricePerUnit ,Integer quantity, String status, Integer ID) {
		super(transactionID, "ORDER", amount, date);
		this.productCode = productCode;
		this.pricePerUnit = pricePerUnit;
		this.quantity = quantity;
		this.status = status;
		this.orderId = ID;
		if(Order.OrderidCounter < ID)
			Order.OrderidCounter = ID;
	}

	@Override
	public int getBalanceId() {
		
		return this.transactionID;
	}
	
	@Override
	public void setBalanceId(Integer balanceId) {
		super.setBalanceId(balanceId);

	}

	@Override
	public String getProductCode() {
		
		return this.productCode;
	}

	@Override
	public void setProductCode(String productCode) {
		this.productCode=productCode;

	}

	@Override
	public double getPricePerUnit() {
		
		return this.pricePerUnit;
	}

	@Override
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit=pricePerUnit;
		this.amount = pricePerUnit * this.quantity;

	}

	@Override
	public int getQuantity() {
		
		return this.quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity=quantity;
		this.amount = this.pricePerUnit * quantity;

	}

	@Override
	public String getStatus() {
		
		return this.status;
	}

	@Override
	public void setStatus(String status) {
		this.status=status;

	}

	@Override
	public Integer getOrderId() {
		
		return orderId;
	}

	@Override
	public void setOrderId(Integer orderId) {
		this.orderId=orderId;

	}

}
