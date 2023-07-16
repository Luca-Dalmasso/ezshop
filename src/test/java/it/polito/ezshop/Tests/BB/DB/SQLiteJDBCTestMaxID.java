package it.polito.ezshop.Tests.BB.DB;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;

public class SQLiteJDBCTestMaxID {

	@Before
	public void resetDB() {
		SQLiteJDBC.reset();
	}

	@Test
	public void testMaxIDWithEmptyDB() {

		Integer res = SQLiteJDBC.maxID();

		assertTrue(res == 0);
	}

	@Test
	public void testMaxIDWithDataOnDB() {

		SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);

		Integer res = SQLiteJDBC.maxID();
		assertTrue(res == 6221);
	}

	@After
	public void finalresetDB() {
		SQLiteJDBC.reset();
	}

}
