
package it.polito.ezshop.model;

import java.util.List;
import java.util.ArrayList;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.*;

public class CreditCardList {

    private List<CreditCard> cardList;

    public CreditCardList(){
        List<CreditCard> lst = SQLiteJDBC.init("CreditCards", CreditCard.class);
    	if(lst == null)
    		this.cardList = new ArrayList<CreditCard>();
    	else
    		this.cardList = lst;
    } 

    public CreditCard searchCardByCode(String code) throws InvalidCreditCardException {
    	if (code == null || code.length() == 0 || !checkCodeValidity(code)) {
    		throw new InvalidCreditCardException();
    	}
    	for(int i=0; i<this.cardList.size(); i++){
    		CreditCard c = this.cardList.get(i);
            if(c.getCreditCardCode().equals(code)){
                return c;
            }
        }
    	return null;
    }
    
    private boolean checkCodeValidity(String creditCardCode) {
    	int sum = 0;
        boolean alternate = false;
        for (int i = creditCardCode.length() - 1; i >= 0; i--) {
        	int n = Integer.parseInt(creditCardCode.substring(i, i + 1));
            if (alternate) {
            	n *= 2;
                if (n > 9) {
                	n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
    
    public boolean checkBalance(String creditCardCode, Double cost) throws InvalidCreditCardException {
    	if (cost==null || cost<0) { return false;}
    	if (creditCardCode == null || creditCardCode.length() == 0 || !checkCodeValidity(creditCardCode)) {
    		throw new InvalidCreditCardException();
    	}
    	CreditCard c = searchCardByCode(creditCardCode);
    	if (c==null) {
    		return false;
    	}
    	return c.getBalance()>=cost;
    }
    
}