package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezshop.database.SQLiteJDBC;

public class AccountBook {
     
	private List<BalanceOperation> accountingList;
	private Double balance;
	
	
	public AccountBook() {
		BalanceOperation.setIdCounter(SQLiteJDBC.maxID());
		List<BalanceOperation> lst = SQLiteJDBC.init("Accounting", BalanceOperation.class);
		this.balance = 0.0;
    	if(lst == null) {
    		this.accountingList = new ArrayList<BalanceOperation>();
    	}
    	else{
    		this.accountingList = lst;
			for(BalanceOperation b : this.accountingList) {
				Double val = b.getMoney();
				if (b.getType().compareTo("RETURN") == 0 || b.getType().compareTo("ORDER") == 0 || b.getType().compareTo("DEBIT") == 0)	
					val = -val;			
				this.balance += val;
			}
    	}
	}

    
	
	public List<BalanceOperation> getAccountingList() {
		return accountingList;
	}


	public Double getBalance() {
		return this.balance;
	}


	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public boolean addBalanceOperation(BalanceOperation op) {
		if (op.getType().compareTo("RETURN") == 0 || op.getType().compareTo("ORDER") == 0 || op.getType().compareTo("DEBIT") == 0) {
			if((this.balance - op.getMoney())<0)
				return false;
			this.balance -= op.getMoney();
		} else
			this.balance += op.getMoney();	
		if(!SQLiteJDBC.insert("Accounting", op.getBalanceId(), op.getType(), op.getMoney(), op.getDate().toString()))
			return false;
		this.accountingList.add(op);
		return true;
	}
	
	public boolean deleteBalanceOperation(Integer transactionID) {
		
		for(BalanceOperation b : this.accountingList) {
			if( b.getBalanceId() == transactionID){
				if (!(b.getType().compareTo("RETURN") == 0 || b.getType().compareTo("ORDER") == 0 || b.getType().compareTo("DEBIT") == 0))
					if (this.balance-b.getMoney() < 0)
						return false;
				if(!SQLiteJDBC.delete("Accounting", b.getBalanceId()))
					return false;
				if (b.getType().compareTo("RETURN") != 0 && b.getType().compareTo("ORDER") != 0 && b.getType().compareTo("DEBIT") != 0)
					this.balance += b.getMoney();
				else
					this.balance -= b.getMoney();
				this.accountingList.remove(b);
				
				return true;
			}
               
		}
		return false;
	}
	

}
