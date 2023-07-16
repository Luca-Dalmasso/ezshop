package it.polito.ezshop.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.InvalidLocationException;
import it.polito.ezshop.exceptions.InvalidPricePerUnitException;
import it.polito.ezshop.exceptions.InvalidProductCodeException;
import it.polito.ezshop.exceptions.InvalidProductDescriptionException;
import it.polito.ezshop.exceptions.InvalidProductIdException;

import java.util.ArrayList;

public class ProductTypeList {

	private List<ProductType> productsList;

	public ProductTypeList() {
		this.productsList = new ArrayList<ProductType>();
		List<ProductType> lst = SQLiteJDBC.init("Inventory", ProductType.class);
		if (lst == null)
			this.productsList = new ArrayList<ProductType>();
		else
			this.productsList = lst;
	}

	public List<ProductType> getProductTypeList() {
		return this.productsList;
	}

	private Integer generateID(List<ProductType> productList) {
		Integer maxID = productList.stream().mapToInt(v -> v.getId()).max().orElse(0);
		return maxID + 1;
	}

	public Integer addProductType(String description, String productCode, Double pricePerUnit, String note,
			String position)
			throws InvalidProductDescriptionException, InvalidPricePerUnitException, InvalidProductCodeException {
		ProductType p = searchProductTypeByBarCode(productCode);
		if (p != null)
			return -1;
		if (description == null || description == "")
			throw new InvalidProductDescriptionException();
		if (pricePerUnit == null || pricePerUnit <= 0)
			throw new InvalidPricePerUnitException();
		Integer generatedID = generateID(this.productsList);
		String newNote = note;
		String newDescription = description;
		if (note == null) {
			newNote = "";
			newDescription = "";
		}

		ProductType prod = new ProductType(generatedID, productCode, newDescription, pricePerUnit, newNote, position);
		if (!SQLiteJDBC.insert("Inventory", prod.getId(), prod.getBarCode(), prod.getProductDescription(),
				prod.getPricePerUnit(), prod.getQuantity(), prod.getNote(), prod.getLocation()))
			return -1;
		this.productsList.add(prod);
		return prod.getId();
	}

	public ProductType searchProductTypeByID(Integer productID) throws InvalidProductIdException {
		if (productID == null || productID <= 0)
			throw new InvalidProductIdException();
		for (ProductType pp : productsList) {
			if (pp.getId().intValue() == productID) {
				return pp;
			}
		}
		return null;
	}

	public ProductType searchProductTypeByBarCode(String barCode) throws InvalidProductCodeException {
		if (barCode == null || barCode.length() == 0)
			throw new InvalidProductCodeException();
		ProductType p = productsList.stream().filter(pr -> (pr.getBarCode().equals(barCode))).findAny().orElse(null);
		return p;
	}

	public List<ProductType> searchProductTypeByDescription(String description) {
		String newDescription = description;
		if (description==null) {
			newDescription = "";
		}
		ArrayList<ProductType> products = new ArrayList<ProductType>();
		for (ProductType p : productsList) {
			if (p.getProductDescription().contains(newDescription))
				products.add(p);
		}
		return products;
	}

	public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
			throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			InvalidProductIdException {
		if (newDescription == null || newDescription == "")
			throw new InvalidProductDescriptionException();
		if (newCode == null || newCode == "")
			throw new InvalidProductCodeException();
		if (newPrice <= 0)
			throw new InvalidPricePerUnitException();
		ProductType p;
		p = searchProductTypeByID(id);
		if (p == null)
			return false;
		String newNote2 = newNote;
		String newDescription2 = newDescription;
		if (newNote2 == null) {
			newNote2 = "";
			newDescription2 = "";
		}
		if (!SQLiteJDBC.update("Inventory", id, newCode, newDescription2, newPrice, p.getQuantity(), newNote2,
				p.getLocation())) {
			return false;

		}
		p.setId(id);
		p.setProductDescription(newDescription);
		p.setBarCode(newNote);
		p.setPricePerUnit(newPrice);
		p.setNote(newNote);
		return true;
	}

	private boolean checkPositionFormat(String position) {
		String regexp = "(\\d+)-([a-zA-Z]+)-(\\d+)";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(position);
		boolean matchFound = matcher.find();
		if (matchFound) {
			return true;
		}
		return false;
	}

	public boolean updatePosition(Integer productId, String newPos)
			throws InvalidProductIdException, InvalidLocationException {
		String pos = newPos;
		if (newPos == null || newPos.length() == 0) {
			pos = "";
		} else {
			if (!checkPositionFormat(newPos)) {
				throw new InvalidLocationException();
			}
			if (productsList.stream().filter(p -> p.getLocation().equals(newPos)).findAny().isPresent()) {
				return false;
			}
		}
		ProductType pt = searchProductTypeByID(productId);
		if (pt == null) {
			return false;
		}
		if (!SQLiteJDBC.update("Inventory", pt.getId(), pt.getBarCode(), pt.getProductDescription(),
				pt.getPricePerUnit(), pt.getQuantity(), pt.getNote(), pos))
			return false;
		pt.setLocation(pos);
		return true;
	}
}
