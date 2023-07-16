package it.polito.ezshop.Tests.WB.SaleTransaction;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.TicketEntry;
import it.polito.ezshop.model.SaleTransaction;

public class SaleTransactionTestGettersSettersConstructors {

	private SaleTransaction sale = null;

	@Before
	public void beforeEachTest() {
		sale = new SaleTransaction();
	}

	@Test
	public void testSettersGettersConstructors() {
		Double disc = 0.5;
		Double price = 100.0;
		String ptype = "thisisapaymenttype";
		Integer tnum = 100;
		String entry = "{ \"barCode\" : \"thisisabarcode\", \"description\" : \"carrots\", \"quantity\" : 15, \"price\" : 0.2, \"discount\" : 0.0 }";
		SaleTransaction sale1 = new SaleTransaction(15, 100.0, "2021-05-01", 20, entry, "OPENED", null, 0.0);
		List<TicketEntry> lst = new ArrayList<TicketEntry>();
		lst.add(new it.polito.ezshop.model.TicketEntry("thisisabarcode", "Tomato", 10, 0.5));

		sale.setDiscountRate(disc);
		sale.setEntries(lst);
		sale.setPaymentType(ptype);
		sale.setPrice(price);
		sale.setState("CLOSED");
		sale.setTicketNumber(tnum);
		sale1.getEntriesToString();

		assertTrue(sale.getDiscountRate() == disc && sale.getEntries().size() == 1
				&& sale.getPaymentType().equals(ptype) && sale.getPrice() == price && sale.getState().equals("CLOSED")
				&& sale.getTicketNumber() == tnum);
	}
}
