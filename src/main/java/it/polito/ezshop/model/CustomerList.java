package it.polito.ezshop.model;

import java.util.List;
import java.util.ArrayList;
import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.*;

public class CustomerList {

    private List<Customer> customerList;

    public CustomerList(){
    	List<Customer> lst = SQLiteJDBC.init("Customers", Customer.class);
    	if(lst == null)
    		this.customerList = new ArrayList<Customer>();
    	else 
    		this.customerList = lst;
    }

    private Integer generateID() {
    	return this.customerList.stream().mapToInt(v -> v.getId()).max().orElse(0) + 1;  
    }

    private void checkCustomerName(String customerName) throws InvalidCustomerNameException {
        if(customerName == null || customerName.equals(""))
            throw new InvalidCustomerNameException();
    }

    private void checkCustomerID(Integer ID) throws InvalidCustomerIdException {
        if(ID == null || ID <= 0)
            throw new InvalidCustomerIdException();
    }

    public Customer getCustomer(Integer id) throws InvalidCustomerIdException {
        checkCustomerID(id);
        Customer c = customerList.stream().filter(cst -> cst.getId()==id).findAny().orElse(null);
        return c;
    }

    public List<Customer> getCustomerList() {
        return this.customerList;
    }

    public Integer addCustomer(String customerName) throws InvalidCustomerNameException {
        checkCustomerName(customerName);
        Integer id = generateID();
		Customer c = new Customer(id, customerName);
        if(!SQLiteJDBC.insert("Customers", c.getId(), c.getCustomerName(), c.getCustomerCard(), c.getPoints())){
            return -1;
        }
        customerList.add(c);
        return c.getId();
    }

    public boolean updateCustomer(Integer id, String newCustomerName, String newCustomerCard) throws InvalidCustomerNameException, InvalidCustomerIdException {
    	checkCustomerID(id);
        checkCustomerName(newCustomerName);
        Customer c = customerList.stream().filter(cst -> cst.getId()==id).findAny().orElse(null);
        if(c == null)
            return false;
        c.setCustomerName(newCustomerName);
        if(newCustomerCard == null) {
        	if(!SQLiteJDBC.update("Customers", c.getId(), c.getCustomerName(), c.getCustomerCard(), c.getPoints()))
                return false;
        } else if (newCustomerCard.equals("")) {
        	if(!SQLiteJDBC.update("Customers", c.getId(), c.getCustomerName(), null, 0))
                return false;
        	c.setCustomerCard(null);
            c.setPoints(0);
		} else {
			if(!SQLiteJDBC.update("Customers", c.getId(), c.getCustomerName(), newCustomerCard, 0))
                return false;
			c.setCustomerCard(newCustomerCard);
        	c.setPoints(0);
		}
        return true;
    }

    public boolean deleteCustomer(Integer customerID) throws InvalidCustomerIdException {
        checkCustomerID(customerID);
         Customer c = customerList.stream().filter(cst -> cst.getId()==customerID).findAny().orElse(null);
        if(c == null)
            return false;
        if(!SQLiteJDBC.delete("Customers", customerID))
            return false;
        customerList.remove(c);
        return true;
    }

    public boolean attachCustomerCard(Integer customerID, String newCustomerCard) throws InvalidCustomerIdException {
        checkCustomerID(customerID);
        Customer c = customerList.stream().filter(cst -> cst.getId()==customerID).findAny().orElse(null);
        if(c == null)
            return false;
        if(!SQLiteJDBC.update("Customers", c.getId(), c.getCustomerName(), newCustomerCard, 0)){
            return false;
        }
        c.setCustomerCard(newCustomerCard);
        c.setPoints(0);
        return true;
    }
}













