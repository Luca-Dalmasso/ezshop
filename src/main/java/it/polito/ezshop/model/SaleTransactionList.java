package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;

public class SaleTransactionList {

	private List<SaleTransaction> saleTransactionList;

    public SaleTransactionList() {
    	BalanceOperation.setIdCounter(SQLiteJDBC.maxID());
    	SaleTransaction.setTicketNumberCounter(0);
    	List<SaleTransaction> lst = SQLiteJDBC.init("SaleTransactions", SaleTransaction.class);
    	if(lst == null)
    		this.saleTransactionList = new ArrayList<SaleTransaction>();
    	else
    		this.saleTransactionList = lst;
	}
    
    private void checkSaleID(Integer SaleID) throws InvalidTransactionIdException {
    	if(SaleID == null || SaleID <= 0)
    		throw new InvalidTransactionIdException();
    }
    
    public Integer addSale() {
    	SaleTransaction sale = new SaleTransaction();
    	this.saleTransactionList.add(sale);
    	return sale.getTicketNumber();
    }
    
    public SaleTransaction searchSale(Integer saleID) throws InvalidTransactionIdException {
    	checkSaleID(saleID);
    	return saleTransactionList.stream().filter(s -> s.getTicketNumber() == saleID).findAny().orElse(null);
    }
    public SaleTransaction getClosedSale(Integer saleID) throws InvalidTransactionIdException {
    	checkSaleID(saleID);
    	return saleTransactionList.stream().filter(s -> (s.getTicketNumber() == saleID && s.getState().equals("CLOSED"))).findAny().orElse(null);
    }
    
    public boolean closeSale(Integer saleID) throws InvalidTransactionIdException {
    	SaleTransaction sale = searchSale(saleID);
    	if(sale == null || !sale.getState().equals("OPENED"))
    		return false;
    	sale.setState("CLOSED");
		if(!SQLiteJDBC.insert("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
				sale.getTicketNumber(), sale.getEntriesToString(), sale.getState(), sale.getPaymentType(),
				sale.getDiscountRate()))
			return false;
		return true;
    }
      
    public boolean deleteSale(Integer saleID) throws InvalidTransactionIdException {
    	SaleTransaction sale = searchSale(saleID);
    	if(sale == null || sale.getState().equals("PAYED"))
    		return false;
    	if(sale.getState().equals("CLOSED"))
    		if(!SQLiteJDBC.delete("SaleTransactions", saleID))
    			return false;
    	saleTransactionList.remove(sale);
    	return true;
    }
}
