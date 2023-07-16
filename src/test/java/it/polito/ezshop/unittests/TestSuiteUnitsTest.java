package it.polito.ezshop.unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.WB.*;
import it.polito.ezshop.Tests.BB.DB.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({BalanceOperationTest.class, 
					 CreditCardTest.class, 
					 CustomerTest.class, 
					 LoyaltyCardTest.class, 
					 OrderTest.class, 
					 ProductTypeTest.class, 
					 ReturnTransactionTest.class,
					 TicketEntryTest.class,
					 UserTest.class,
					 SQLiteJDBCTestDelete.class,
					 SQLiteJDBCTestInsert.class,
					 SQLiteJDBCTestMaxID.class,
					 SQLiteJDBCTestUpdate.class
					})

public class TestSuiteUnitsTest {

}
