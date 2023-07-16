package it.polito.ezshop.Tests.WB;

import org.junit.Test;

import it.polito.ezshop.model.TicketEntry;

public class TicketEntryTest {
	
	@Test
	public void testConstructorSetGet() {
		TicketEntry t = new TicketEntry("invalid barcode", "invalid description", 10, 12.5);
		t.setBarCode(t.getBarCode());
		t.setProductDescription(t.getProductDescription());
		t.setAmount(t.getAmount());
		t.setPricePerUnit(t.getPricePerUnit());
		t.setDiscountRate(13);
		t.setDiscountRate(t.getDiscountRate()+5);
	}

}
