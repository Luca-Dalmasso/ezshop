package it.polito.ezshop.Tests.BB.DB;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;

public class SQLiteJDBCTestUpdate {

	@Before
	public void resetDB() {
		SQLiteJDBC.reset();
	}

	@Test
	public void testUpdateRow() {

		boolean[] res = new boolean[9];
		boolean[] actual = new boolean[9];
		Arrays.fill(actual, true);
		
		SQLiteJDBC.insert("Customers", 1234, "Mark", null, 0);
		SQLiteJDBC.insert("Users", 15, "John", "mypsw", "Administrator");
		SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", 4.90, 10, "zzzz", "12-AA-32");
		SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);
		res[7] = SQLiteJDBC.insert("LoyaltyCards", "4963582195", 1000);
		
		res[0] = SQLiteJDBC.update("Customers", 1234, "Luke", "sgfedkgfsfjes", 100);
		res[1] = SQLiteJDBC.update("Users", 15, "John", "myNEWpsw", "Administrator");
		res[2] = SQLiteJDBC.update("Inventory", 1256, "xxxxyyyy", "aaaa", 4.90, 10, "zzzz", "12-AA-35");
		res[3] = SQLiteJDBC.update("Accounting", 5874, "ORDER", 57.20, "2021-05-14");
		res[4] = SQLiteJDBC.update("SaleTransactions", 6221, 123.90, "2021-04-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 19 , \"price\" : 12.40 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		res[5] = SQLiteJDBC.update("ReturnTransactions", 1478, 25.50, "2021-06-13", "CLOSED", "igkdcurtiwrhgsgssdf", 6, 0333);
		res[6] = SQLiteJDBC.update("Orders", 5597, 108.00, "2021-03-01", "fgrgkrixhfffrovs", 9.00, 12, "PAYED", 5597);
		res[7] = SQLiteJDBC.update("CreditCards", "4485370086510891", 108.00);
		res[8] = SQLiteJDBC.update("LoyaltyCards", "4963582195", 1100);

		assertArrayEquals(res, actual);
	}

	@Test
	public void testUpdateWrongNumberOfParams() {

		boolean[] res = new boolean[9];
		boolean[] actual = new boolean[9];

		res[0] = SQLiteJDBC.update("Customers", 1234);
		res[1] = SQLiteJDBC.update("Users", 15, "John", "Administrator");
		res[2] = SQLiteJDBC.update("Inventory", 1256, "xxxx", "aaaa", "zzzz", "12-AA-32");
		res[3] = SQLiteJDBC.update("Accounting");
		res[4] = SQLiteJDBC.update("SaleTransactions", 6221, 123.90, "2021-03-18", 158749, "CLOSED", null, 0.1);
		res[5] = SQLiteJDBC.update("ReturnTransactions", 1478, 25.50, "2021-04-13", "igkdcurtiwads", 6, 0333);
		res[6] = SQLiteJDBC.update("Orders", 5597, 108.00, "PAYED", 5597);
		res[7] = SQLiteJDBC.update("CreditCards", "4485370086510891");
		res[8] = SQLiteJDBC.update("LoyaltyCards");

		assertArrayEquals(res, actual);
	}

	@Test
	public void testTableNameNotExist() {

		boolean res = SQLiteJDBC.update("FakeTableName", 9999, "Name");

		assertEquals(res, false);
	}

	@After
	public void finalresetDB() {
		SQLiteJDBC.reset();
	}
}
