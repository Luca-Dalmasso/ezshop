package it.polito.ezshop.Tests.WB;

import org.junit.Test;

import it.polito.ezshop.model.ProductType;

public class ProductTypeTest {
	
	@Test
	public void testConstructorSetGet() {
		ProductType product = new ProductType(1,"17ahs7h2", "very very useless product", 0.01, "very very useless notes", "wrong position");
		Integer pid = product.getId();
		String pcode = product.getBarCode();
		String descr = product.getProductDescription();
		Double price = product.getPricePerUnit();
		String note = product.getNote();
		String position = product.getLocation();
		product.setId(pid);
		product.setBarCode(pcode);
		product.setProductDescription(descr);
		product.setPricePerUnit(price);
		product.setNote(note);
		product.setLocation(position);
		product.setQuantity(100);
		ProductType product2 = new ProductType(1,"17ahs7h2", "very very useless product", 0.01,2, "very very useless notes", "wrong position");
		product2.setQuantity(product.getQuantity());
	}

}
