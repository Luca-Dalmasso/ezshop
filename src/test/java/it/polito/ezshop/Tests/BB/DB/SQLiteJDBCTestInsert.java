package it.polito.ezshop.Tests.BB.DB;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import it.polito.ezshop.database.SQLiteJDBC;

public class SQLiteJDBCTestInsert {

	@Before
	public void resetDB() {
		SQLiteJDBC.reset();
	}

	@Test
	public void testInsertRow() {

		boolean[] res = new boolean[8];
		boolean[] actual = new boolean[8];
		Arrays.fill(actual, true);
		
		res[0] = SQLiteJDBC.insert("Customers", 1234, "Mark", null, 0);
		res[1] = SQLiteJDBC.insert("Users", 15, "John", "mypsw", "Administrator");
		res[2] = SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", 4.90, 10, "zzzz", "12-AA-32");
		res[3] = SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		res[4] = SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		res[5] = SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		res[6] = SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);
		res[7] = SQLiteJDBC.insert("LoyaltyCards", "4963582195", 1000);

		assertArrayEquals(res, actual);
	}

	@Test
	public void testRowAlreadyExist() {

		boolean[] res = new boolean[8];
		boolean[] actual = new boolean[8];

		SQLiteJDBC.insert("Customers", 1234, "Mark", null, 0);
		SQLiteJDBC.insert("Users", 15, "John", "mypsw", "Administrator");
		SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", 4.90, 10, "zzzz", "12-AA-32");
		SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);
		SQLiteJDBC.insert("LoyaltyCards", "4963582195", 1000);
		res[0] = SQLiteJDBC.insert("Customers", 1234, "Mark", null, 0);
		res[1] = SQLiteJDBC.insert("Users", 15, "John", "mypsw", "Administrator");
		res[2] = SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", 4.90, 10, "zzzz", "12-AA-32");
		res[3] = SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		res[4] = SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		res[5] = SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		res[6] = SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);
		res[7] = SQLiteJDBC.insert("LoyaltyCards", "4963582195", 1000);

		assertArrayEquals(res, actual);
	}

	@Test
	public void testInsertWrongNumberOfParams() {

		boolean[] res = new boolean[8];
		boolean[] actual = new boolean[8];

		res[0] = SQLiteJDBC.insert("Customers", 1234, null, 0);
		res[1] = SQLiteJDBC.insert("Users", 15, "John", "Administrator");
		res[2] = SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", "zzzz", "12-AA-32");
		res[3] = SQLiteJDBC.insert("Accounting");
		res[4] = SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749, "CLOSED", null, 0.1);
		res[5] = SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "igkdcurtiwads", 6, 0333);
		res[6] = SQLiteJDBC.insert("Orders", 5597, 108.00, "PAYED", 5597);
		res[7] = SQLiteJDBC.insert("LoyaltyCards");

		assertArrayEquals(res, actual);
	}

	@Test
	public void testTableNameNotExist() {

		boolean res = SQLiteJDBC.insert("FakeTableName", 9999, "Name");

		assertEquals(res, false);
	}

	@After
	public void finalresetDB() {
		SQLiteJDBC.reset();
	}
}
