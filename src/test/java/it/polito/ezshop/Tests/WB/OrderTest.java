package it.polito.ezshop.Tests.WB;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polito.ezshop.model.Order;

public class OrderTest {

	@SuppressWarnings("unused")
	@Test
	public void testConstuctorsSettersGetters() {

		Integer id = 1000;
		Integer orderID = 5422;
		Double price = 50.00;
		Integer qty = 10;
		String pCode = "bbbbbbbbbbbbb";
		String status = "COMPLETED";
		Order o = new Order("aaaaaaaaaaaaa", 100.00, 1, "ISSUED");
		Order o1 = new Order(id, qty * price, "2021-01-01", pCode, price, qty, status, orderID);

		o.setBalanceId(id);
		o.setOrderId(orderID);
		o.setPricePerUnit(price);
		o.setProductCode(pCode);
		o.setQuantity(qty);
		o.setStatus(status);

		assertTrue(o.getBalanceId() == id && o.getOrderId() == orderID && o.getPricePerUnit() == price
				&& o.getProductCode() == pCode && o.getQuantity() == qty && o.getStatus() == status);
	}
}
