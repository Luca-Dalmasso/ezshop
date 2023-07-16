package it.polito.ezshop.Tests.WB;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import it.polito.ezshop.model.BalanceOperation;

public class BalanceOperationTest {

	@SuppressWarnings("unused")
	@Test
	public void testConstuctorsSettersGetters() {

		Integer id = 1000;
		LocalDate date = LocalDate.now();
		String type = "RETURN";
		Double money = 100.50;
		BalanceOperation b = new BalanceOperation("SALE", 50.0);
		BalanceOperation b1 = new BalanceOperation(id, type, money, "2021-03-22");
		b.setBalanceId(id);
		b.setDate(date);
		b.setType(type);
		b.setMoney(money);
		BalanceOperation.setIdCounter(0);

		assertTrue(b.getBalanceId() == id && b.getDate() == date && b.getType() == type && b.getMoney() == money);

	}
}
