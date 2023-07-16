package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.ReturnTransactionList.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ReturnTransactionListTestAddReturn.class, 
					 ReturnTransactionListTestCloseReturnTransaction.class, 
					 ReturnTransactionListTestDeleteReturnTransaction.class,
					 ReturnTransactionListTestSearchReturnTransaction.class,
					})
public class TestSuiteReturnTransactionList {

}
