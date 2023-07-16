package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.WB.SaleTransaction.SaleTransactionTestAddProduct;
import it.polito.ezshop.Tests.WB.SaleTransaction.SaleTransactionTestDeleteProduct;
import it.polito.ezshop.Tests.WB.SaleTransaction.SaleTransactionTestGettersSettersConstructors;
import it.polito.ezshop.Tests.WB.SaleTransaction.SaleTransactionTestSetDiscountToProduct;

@RunWith(Suite.class)
@Suite.SuiteClasses({SaleTransactionTestAddProduct.class,
					 SaleTransactionTestDeleteProduct.class,
					 SaleTransactionTestGettersSettersConstructors.class,
					 SaleTransactionTestSetDiscountToProduct.class
					})

public class TestSuiteSaleTransaction {

}
