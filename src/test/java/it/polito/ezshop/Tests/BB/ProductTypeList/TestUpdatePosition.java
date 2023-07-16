package it.polito.ezshop.Tests.BB.ProductTypeList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.data.ProductType;
import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.model.ProductTypeList;

public class TestUpdatePosition {
	
private ProductTypeList plist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		plist = new ProductTypeList();
	}
	
	
	@Test
	public void testNullID() {
		assertThrows(InvalidProductIdException.class,()->{plist.updatePosition(null, "00-AA-00");});
	}
	
	@Test
	public void testNegID() {
		assertThrows(InvalidProductIdException.class,()->{plist.updatePosition(0, "00-AA-00");});		
	}
	
	
	@Test
	public void testValidButIncorrectPosition() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description0", "code0", 1.00, "note0", "00-aa-00");
		assertTrue(productID>0);
		assertThrows(InvalidLocationException.class,()->{plist.updatePosition(1, "ax14");});		
	}
	
	
	@Test
	public void testDuplicatedPosition() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidProductCodeException {
		//addProduct("description","code",1.00,"note","00-AA-00")->return id=1<br>addProduct("description2","code2",1.00,"note2","01-AA-00")->return id=2<br>T(2,"00-AA-00")->InvalidLocationException		
		Integer productID = plist.addProductType("description", "code", 1.00, "note", "00-AA-00");
		assertTrue(productID>0);
		productID = plist.addProductType("description2", "code2", 1.00, "note2", "01-AA-00");
		assertTrue(productID>0);
		assertFalse(plist.updatePosition(2, "00-AA-00"));
	}
	
	
	@Test
	public void testNullPosition() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description3", "code3", 1.00, "note3", "02-AA-00");
		assertTrue(productID>0);
		assertTrue(plist.updatePosition(productID, null));
		ProductType p= plist.searchProductTypeByID(productID);
		assertTrue(p.getLocation().length()==0);
	}
	
	
	@Test
	public void testEmptyPosition() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description4", "code4", 1.00, "note4", "02-AA-00");
		assertTrue(productID>0);
		assertTrue(plist.updatePosition(productID, ""));
		ProductType p= plist.searchProductTypeByID(productID);
		assertTrue(p.getLocation().length()==0);
	}
	
	@Test
	public void testValidFormatUpdate() throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductIdException, InvalidLocationException, InvalidProductCodeException {
		Integer productID = plist.addProductType("description5", "code5", 1.00, "note5", "02-AA-00");
		assertTrue(productID>0);
		assertTrue(plist.updatePosition(productID, "03-AA-00"));		
	}
	
	
}
