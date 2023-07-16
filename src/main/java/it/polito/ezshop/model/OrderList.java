package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidOrderIdException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidQuantityException;

public class OrderList{

	private List<Order> ordersList;
	
	public OrderList() {
		BalanceOperation.setIdCounter(SQLiteJDBC.maxID());
		List<Order> lst = SQLiteJDBC.init("Orders", Order.class);
    	if(lst == null)
    		this.ordersList = new ArrayList<Order>();
    	else
    		this.ordersList = lst;
	} 
    
	public List<Order> getAllOrders() {
		return ordersList;
	}
	
	public Integer addOrder(Double amount,String productCode, Double pricePerUnit ,int quantity, String status) throws InvalidQuantityException, InvalidPricePerUnitException {
		if(quantity<=0) { throw new InvalidQuantityException();}
		if(pricePerUnit<=0) { throw new InvalidPricePerUnitException();}
		Order ord = new Order(productCode,  pricePerUnit,  quantity,  status);
		if(!SQLiteJDBC.insert("Orders", ord.getBalanceId(),ord.getMoney(),ord.getDate().toString(),ord.getProductCode(),ord.getPricePerUnit(),ord.getQuantity(),ord.getStatus(),ord.getOrderId())){
			return -1;
		}
		this.ordersList.add(ord);
		return ord.getBalanceId();
	}
	
	public Order searchOrderByID(Integer orderId) throws InvalidOrderIdException{
		if(orderId==null || orderId<=0) {throw new InvalidOrderIdException();}
		Order o = this.ordersList.stream().filter(ord -> (ord.getBalanceId()==orderId)).findAny().orElse(null);
        return o;
	}
	
	public Order searchOrderByFields(String productCode, int quantity, Double pricePerUnit) throws InvalidPricePerUnitException, InvalidQuantityException{
		if (quantity<=0) {throw new InvalidQuantityException();}
		if (pricePerUnit<=0) { throw new InvalidPricePerUnitException();}
		for(Order o : this.ordersList)
			if( o.getProductCode()==productCode && o.getQuantity() == quantity && o.getPricePerUnit()==pricePerUnit)
				return o;
        return null;
	}
	
	
	public Boolean changeOrderStatus(String status, Integer orderId) throws  InvalidOrderIdException{ 
		Order o =this.searchOrderByID(orderId);
		if(o == null)
			return false;
		//amount = ?,date = ?,barCode = ?,purchasePrice = ?,quantity = ?,status = ?,ID =
		if(!SQLiteJDBC.update("Orders", o.getBalanceId(),o.getMoney(), o.getDate().toString(), o.getProductCode(), o.getPricePerUnit(), o.getQuantity(), status, o.getOrderId())){
			return false;
		}
		o.setStatus(status);
		return true;
	}

}
