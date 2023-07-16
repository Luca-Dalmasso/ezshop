package it.polito.ezshop.Tests.BB.ProductTypeList;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.model.ProductTypeList;

public class TestSearchProductByDescription {
	
	private ProductTypeList plist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		plist = new ProductTypeList();
	}
	
	@Test
	public void testNullDescription() throws InvalidProductCodeException {
			plist.searchProductTypeByDescription(null);
	}
	
	@Test
	public void testEmptyDescription() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		plist.addProductType("whatever","whatever4",1.00,null,"");
		assertTrue(plist.searchProductTypeByDescription("").size()==1);
	}
	
	@Test
	public void testValidSearch() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		assertTrue(plist.searchProductTypeByDescription("a").size()==0);
		plist.addProductType("a","whatever4",1.00,"xx","");
		assertTrue(plist.searchProductTypeByDescription("a").size()==1);
	}

}
