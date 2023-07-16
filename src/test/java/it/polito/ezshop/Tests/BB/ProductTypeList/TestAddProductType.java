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


public class TestAddProductType {
	
	private ProductTypeList plist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		plist = new ProductTypeList();
	}
	
	
	@Test
	public void testNullDescription() throws InvalidProductDescriptionException {
		assertThrows(InvalidProductDescriptionException.class, () -> { plist.addProductType(null, "whatever", 10.00, "whatever", "whatever"); } );
	}
	
	@Test
	public void testEmptyDescription() {
		assertThrows(InvalidProductDescriptionException.class, () -> { plist.addProductType("", "whatever", 10.00, "whatever", "whatever"); } );
	}

	@Test	
	public void testNullPrice() {
		assertThrows(InvalidPricePerUnitException.class, () -> { plist.addProductType("whatever", "whatever", null, "whatever", "whatever"); } );
	}

	@Test
	public void testNegPrice() {
		assertThrows(InvalidPricePerUnitException.class, () -> { plist.addProductType("whatever", "whatever", -10.00, "whatever", "whatever"); } );
	}

	
	@Test
	public void testDuplicatedDesc() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description1","code",1.00,"whatever",null);
		assertTrue(productID>0);
		productID = plist.addProductType("description2","code",1.00,"whatever","");
		assertTrue(productID==-1);
	}
	
	
	@Test
	public void testDuplicatedCode() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description1","code",1.00,"whatever",null);
		assertTrue(productID>0);
		productID = plist.addProductType("description1","code",1.00,"whatever","");
		assertTrue(productID==-1);
	}
	

	@Test
	public void testValidProductType() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidProductCodeException {
		Integer productID = plist.addProductType("whatever", "whatever1", 10.00, "whatever", "");
		assertTrue(productID>0);
		productID = plist.addProductType("whatever1", "whatever2", 10.00, "", "whatever");
		assertTrue(productID>0);
		productID = plist.addProductType("whatever2", "whatever3", 10.00, "", "");
		assertTrue(productID>0);
		productID = plist.addProductType("whatever3", "xxxxx", 10.00, null, "");
		assertTrue(productID>0);
		ProductType p = plist.searchProductTypeByID(productID);
		assertTrue(p.getProductDescription().length()==0);
	}

}
