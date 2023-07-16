package it.polito.ezshop.Tests.BB.DB;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.model.BalanceOperation;
import it.polito.ezshop.model.Customer;
import it.polito.ezshop.model.Order;
import it.polito.ezshop.model.ProductType;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransaction;
import it.polito.ezshop.model.User;

public class SQLiteJDBCTestDelete {

	@Before
	public void resetDB() {
		SQLiteJDBC.reset();
	}

	@Test
	public void testDeleteRow() {

		boolean[] res = new boolean[7];
		boolean[] actual = new boolean[7];
		Arrays.fill(actual, true);
		
		SQLiteJDBC.insert("Customers", 1234, "Mark");
		SQLiteJDBC.insert("Users", 15, "John", "mypsw", "Administrator");
		SQLiteJDBC.insert("Inventory", 1256, "xxxx", "aaaa", 4.90, 10, "zzzz", "12-AA-32");
		SQLiteJDBC.insert("Accounting", 5874, "ORDER", 57.20, "2021-05-12");
		SQLiteJDBC.insert("SaleTransactions", 6221, 123.90, "2021-03-18", 158749,
				"{ \"barCode\" : \"gocbreisoruvv\" , \"description\" : \"xxxx\" , \"quantity\" : 20 , \"price\" : 12.50 , \"discount\" : 1.0 }",
				"CLOSED", null, 0.1);
		SQLiteJDBC.insert("ReturnTransactions", 1478, 25.50, "2021-04-13", "CLOSED", "igkdcurtiwads", 6, 0333);
		SQLiteJDBC.insert("Orders", 5597, 108.00, "2021-01-01", "fgrgkrixhrovs", 9.00, 12, "PAYED", 5597);
		
		SQLiteJDBC.delete("Customers", 1234);
		SQLiteJDBC.delete("Users", 15);
		SQLiteJDBC.delete("Inventory", 1256);
		SQLiteJDBC.delete("Accounting", 5874);
		SQLiteJDBC.delete("SaleTransactions", 6221);
		SQLiteJDBC.delete("ReturnTransactions", 1478);
		SQLiteJDBC.delete("Orders", 5597);
		
		res[0] = (SQLiteJDBC.init("Customers", Object.class)).isEmpty();
		res[1] = (SQLiteJDBC.init("Users", Object.class)).isEmpty();
		res[2] = (SQLiteJDBC.init("Inventory", Object.class)).isEmpty();
		res[3] = (SQLiteJDBC.init("Accounting", Object.class)).isEmpty();
		res[4] = (SQLiteJDBC.init("SaleTransactions", Object.class)).isEmpty();
		res[5] = (SQLiteJDBC.init("ReturnTransactions", Object.class)).isEmpty();
		res[6] = (SQLiteJDBC.init("Orders", Object.class)).isEmpty();
		
		assertArrayEquals(res, actual);
	}
	
	@Test
	public void testDeleteRowDoesntExist() {

		boolean[] res = new boolean[7];
		boolean[] actual = new boolean[7];
		
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
		
		SQLiteJDBC.delete("Customers", 0000);
		SQLiteJDBC.delete("Users", 0000);
		SQLiteJDBC.delete("Inventory", 0000);
		SQLiteJDBC.delete("Accounting", 0000);
		SQLiteJDBC.delete("SaleTransactions", 0000);
		SQLiteJDBC.delete("ReturnTransactions", 0000);
		SQLiteJDBC.delete("Orders", 0000);
		
		res[0] = (SQLiteJDBC.init("Customers", Customer.class)).isEmpty();
		res[1] = (SQLiteJDBC.init("Users", User.class)).isEmpty();
		res[2] = (SQLiteJDBC.init("Inventory", ProductType.class)).isEmpty();
		res[3] = (SQLiteJDBC.init("Accounting", BalanceOperation.class)).isEmpty();
		res[4] = (SQLiteJDBC.init("SaleTransactions", SaleTransaction.class)).isEmpty();
		res[5] = (SQLiteJDBC.init("ReturnTransactions", ReturnTransaction.class)).isEmpty();
		res[6] = (SQLiteJDBC.init("Orders", Order.class)).isEmpty();
		

		assertArrayEquals(res, actual);
	}
	
	@Test
	public void testTableNameNotExist() {

		boolean res = SQLiteJDBC.delete("FakeTableName", 9999);

		assertEquals(res, false);
	}

	@After
	public void finalresetDB() {
		SQLiteJDBC.reset();
	}

}
