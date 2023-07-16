package it.polito.ezshop.Tests.WB;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import it.polito.ezshop.model.CreditCard;

public class CreditCardTest {

	@Test
	public void testConstuctorsSettersGetters() {

		String code = "4485370086510891";
		Double money = 150.00;;
		CreditCard card = new CreditCard(code, 200.00);
		
		card.setBalance(money);
		assertTrue(card.getBalance()==money);
		assertTrue(card.getCreditCardCode()==code);
	}
}

//TODO remove string method in creditcard