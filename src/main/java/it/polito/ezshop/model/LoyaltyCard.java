package it.polito.ezshop.model;

public class LoyaltyCard {
	private String cardID;
	private Integer points;
	
	public LoyaltyCard(String cardID, Integer points) {
		this.cardID=cardID;
		this.points=points;
	}
	
	public String getCardID() {
		return cardID;
	}
	
	public Integer getPoints() {
		return points;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}
	

}
