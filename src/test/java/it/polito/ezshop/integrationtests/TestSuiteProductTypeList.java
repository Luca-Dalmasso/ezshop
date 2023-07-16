package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.ProductTypeList.TestAddProductType;
import it.polito.ezshop.Tests.BB.ProductTypeList.TestSearchProductByBarcode;
import it.polito.ezshop.Tests.BB.ProductTypeList.TestSearchProductByDescription;
import it.polito.ezshop.Tests.BB.ProductTypeList.TestSearchProductByID;
import it.polito.ezshop.Tests.BB.ProductTypeList.TestUpdatePosition;
import it.polito.ezshop.Tests.BB.ProductTypeList.TestUpdateProductType;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestAddProductType.class, 
	TestSearchProductByBarcode.class, 
	TestSearchProductByDescription.class, 
	TestSearchProductByID.class, 
	TestUpdatePosition.class, 
	TestUpdateProductType.class,
					})
public class TestSuiteProductTypeList {

}
