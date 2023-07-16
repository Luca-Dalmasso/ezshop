package it.polito.ezshop.Tests.WB;

import org.junit.Test;

import it.polito.ezshop.model.ReturnTransaction;

public class ReturnTransactionTest {
	
	@Test
	public void testConstructorSetGet() {
		ReturnTransaction rt = new ReturnTransaction(1, "Return", 13.2);
		rt.setProductCode("invalid pcode");
		rt.setQuantity(10);
		rt.setSaleTransactionID(1);
		ReturnTransaction rtdb = new ReturnTransaction(rt.getBalanceId(),rt.getMoney(),rt.getDate().toString(),rt.getStatus(),rt.getProductCode(),rt.getQuantity(),rt.getSaleTransactionID());
		rtdb.setStatus("Closed");
	}

}
