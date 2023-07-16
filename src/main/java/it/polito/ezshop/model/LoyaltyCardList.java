package it.polito.ezshop.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidCustomerCardException;

public class LoyaltyCardList {
	
	private List<LoyaltyCard> cardlist;
	
	private String generateRandomOn10Digit() {
	    Random random = new Random();
	    int codeArray[] = new int [10];
	    int i;
	    for (i=0;i<10;i++)
	    	codeArray[i] = random.ints(0, 10).findFirst().getAsInt();
	    String code = Arrays.stream(codeArray).mapToObj(e -> String.valueOf(e)).reduce("", (a,b) -> a + b);
	    if(cardlist.stream().filter(c -> c.getCardID().equals(code)).findAny().isPresent())
	    	return generateRandomOn10Digit();
	    return code;
	}
	
	public LoyaltyCardList() {
		cardlist = new ArrayList<LoyaltyCard>();
		List<LoyaltyCard> lst = SQLiteJDBC.init("LoyaltyCards", LoyaltyCard.class);
    	if(lst == null)
    		this.cardlist = new ArrayList<LoyaltyCard>();
    	else
    		this.cardlist = lst;
	}
	
	public String addLoyaltyCard() {
		String code = generateRandomOn10Digit();
		if(!SQLiteJDBC.insert("LoyaltyCards",code,0)){
			return "";
		}
		cardlist.add(new LoyaltyCard(code,0));
		return code;	
	}
	
	public boolean addLoyaltyCard(String id) throws InvalidCustomerCardException {
		if(searchLoyaltyCardById(id) != null)
			return false;
		if(!SQLiteJDBC.insert("LoyaltyCards",id,0)){
			return false;
		}
		cardlist.add(new LoyaltyCard(id,0));
		return true;	
	}
	
	public LoyaltyCard searchLoyaltyCardById(String id) throws InvalidCustomerCardException {
		if(id==null || !id.matches("\\d{10}")) {throw new InvalidCustomerCardException();}
		return cardlist.stream().filter(v -> v.getCardID().equals(id)).findAny().orElse(null);
	}
	
	public boolean updatePointsOnCard(String customerCard, int pointsToBeAdded) throws InvalidCustomerCardException {
		LoyaltyCard card = searchLoyaltyCardById(customerCard);
		if (card==null) 
			return false;
		Integer points = card.getPoints()+pointsToBeAdded;
		if((points)<0)
			return false;
		if(!SQLiteJDBC.update("LoyaltyCards", customerCard, points)){
			return false;
		}
		card.setPoints(points);
		return true;
	}

	public boolean deleteLoyaltyCard(String id) throws InvalidCustomerCardException {
		LoyaltyCard card = searchLoyaltyCardById(id);
		if(card == null)
			return false;
		if(!SQLiteJDBC.delete("LoyaltyCards", id))
			return false;
		cardlist.remove(card);
		return true;
	}
}
