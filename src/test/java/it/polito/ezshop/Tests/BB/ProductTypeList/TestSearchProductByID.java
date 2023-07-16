package it.polito.ezshop.Tests.BB.ProductTypeList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.ProductType;
import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.model.ProductTypeList;

public class TestSearchProductByID {
	
	private ProductTypeList plist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		plist = new ProductTypeList();
	}
	
	@Test
	public void testNullID() {
		assertThrows(InvalidProductIdException.class, () -> { plist.searchProductTypeByID(null); } );
	}
	
	@Test
	public void testNegID() {
		assertThrows(InvalidProductIdException.class, () -> { plist.searchProductTypeByID(0); } );
		assertThrows(InvalidProductIdException.class, () -> { plist.searchProductTypeByID(-1); } );
	}
	
	@Test
	public void testValidSearch() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidProductCodeException {
		Integer productID = plist.addProductType("whatever", "whatever1", 10.00, "whatever", "");
		ProductType p = plist.searchProductTypeByID(1);
		assertTrue(productID==p.getId());
	}

}
