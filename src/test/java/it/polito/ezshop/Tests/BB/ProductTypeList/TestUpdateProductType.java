package it.polito.ezshop.Tests.BB.ProductTypeList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;
import it.polito.ezshop.model.ProductTypeList;

public class TestUpdateProductType {
	
	private ProductTypeList plist;
	
	@Before
	public void init() {
		SQLiteJDBC.reset();
		plist = new ProductTypeList();
	}
	
	@Test
	public void testNullProductID() {
		assertThrows(InvalidProductIdException.class,()->{plist.updateProduct(null, "des", "code", 1.00, null);});
	}
	
	@Test
	public void testNegProductID() {
		assertThrows(InvalidProductIdException.class,()->{plist.updateProduct(0, "des", "code", 1.00, null);});		
	}
	
	@Test
	public void testNullDescription() {
		assertThrows(InvalidProductDescriptionException.class,()->{plist.updateProduct(10, null, "code", 1.00, null);});	
	}
	
	@Test
	public void testEmptyDescription() {
		assertThrows(InvalidProductDescriptionException.class,()->{plist.updateProduct(10, "", "code", 1.00, null);});	
	}
	
	@Test
	public void testNegPricePerUnit() {
		assertThrows(InvalidPricePerUnitException.class,()->{plist.updateProduct(10, "des", "code", 0.00, null);});	
	}
	
	@Test
	public void testValidUpdate() throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException, InvalidProductIdException {
		assertFalse(plist.updateProduct(1,"a","1",1.00,null));
		Integer id = plist.addProductType("a","1",1.00,null,null);
		assertTrue(plist.updateProduct(id,"a","1",1.00,null));	
	}
	
	
	

}
