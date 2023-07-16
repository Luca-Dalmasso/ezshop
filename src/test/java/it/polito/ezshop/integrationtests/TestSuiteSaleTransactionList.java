package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.SaleTransactionList.SaleTransactionListTestAddSale;
import it.polito.ezshop.Tests.BB.SaleTransactionList.SaleTransactionListTestCloseSale;
import it.polito.ezshop.Tests.BB.SaleTransactionList.SaleTransactionListTestDeleteSale;
import it.polito.ezshop.Tests.BB.SaleTransactionList.SaleTransactionListTestGetClosedSale;
import it.polito.ezshop.Tests.BB.SaleTransactionList.SaleTransactionListTestSearchSale;


@RunWith(Suite.class)
@Suite.SuiteClasses({SaleTransactionListTestAddSale.class,
					 SaleTransactionListTestCloseSale.class,
					 SaleTransactionListTestDeleteSale.class,
					 SaleTransactionListTestGetClosedSale.class,
					 SaleTransactionListTestSearchSale.class
					})

public class TestSuiteSaleTransactionList {

}
