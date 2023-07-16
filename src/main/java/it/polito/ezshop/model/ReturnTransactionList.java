package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidTransactionIdException;

public class ReturnTransactionList {

    private List<ReturnTransaction> returnTransactionList;

    public ReturnTransactionList(){
    	BalanceOperation.setIdCounter(SQLiteJDBC.maxID());
        List<ReturnTransaction> lst = SQLiteJDBC.init("ReturnTransactions", ReturnTransaction.class);
    	if(lst == null)
    		this.returnTransactionList = new ArrayList<ReturnTransaction>();
    	else
    		this.returnTransactionList = lst;
    }

    public Integer addReturn(Integer transactionID) throws InvalidTransactionIdException{
         if(transactionID == null || transactionID <= 0) throw new InvalidTransactionIdException();
         ReturnTransaction t = new ReturnTransaction(transactionID,"RETURN",0.0);
         returnTransactionList.add(t);
         return t.getBalanceId();
    }
    
	public ReturnTransaction searchReturnTransaction(Integer transactionID) throws InvalidTransactionIdException{
        if(transactionID == null || transactionID <= 0) throw new InvalidTransactionIdException();
        ReturnTransaction t = returnTransactionList.stream().filter((v -> v.getBalanceId()==transactionID)).findAny().orElse(null);
        return t;
    }

    public boolean closeReturnTransaction(Integer transactionID) throws InvalidTransactionIdException{
        if(transactionID == null || transactionID <= 0) throw new InvalidTransactionIdException();
        ReturnTransaction t = returnTransactionList.stream().filter((v -> v.getBalanceId()==transactionID)).findAny().orElse(null);
        if (t==null){ return false;}
        if(!SQLiteJDBC.insert("ReturnTransactions",t.getSaleTransactionID(), t.getMoney(), t.getDate().toString(), "CLOSED", t.getProductCode(), t.getQuantity(), t.getBalanceId())){
            return false;
        }
        t.setStatus("CLOSED");
        return true;
    }

    public boolean deleteReturnTransaction(Integer transactionID) throws InvalidTransactionIdException{
        if(transactionID == null || transactionID <= 0) throw new InvalidTransactionIdException();
        ReturnTransaction t = returnTransactionList.stream().filter((v -> v.getBalanceId()==transactionID)).findAny().orElse(null);
        if (t==null){ return false;}
        if(!SQLiteJDBC.delete("ReturnTransactions", transactionID)){
            return false;
        }
        returnTransactionList.remove(t);
        return true;
    }


}
