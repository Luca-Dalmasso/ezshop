package it.polito.ezshop.data;

import it.polito.ezshop.database.SQLiteJDBC;
import it.polito.ezshop.exceptions.*;
import it.polito.ezshop.model.AccountBook;
import it.polito.ezshop.model.CreditCard;
import it.polito.ezshop.model.CreditCardList;
import it.polito.ezshop.model.CustomerList;
import it.polito.ezshop.model.LoyaltyCard;
import it.polito.ezshop.model.LoyaltyCardList;
import it.polito.ezshop.model.OrderList;
import it.polito.ezshop.model.Product;
import it.polito.ezshop.model.ProductTypeList;
import it.polito.ezshop.model.ReturnTransaction;
import it.polito.ezshop.model.SaleTransactionList;
import it.polito.ezshop.model.UserList;
import it.polito.ezshop.model.ReturnTransactionList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
 * EXCEPTION CONTROLLED BY THIS CLASS ARE THE SHARED ONES: 
 * 1) InvalidProductCodeException(); --> checkCodeValidity(String productCode)
 * 2) throw new UnauthorizedException(); --> checkAutority()
 */

public class EZShop implements EZShopInterface {

	private UserList userlist = new UserList();
	private ProductTypeList productlist = new ProductTypeList();
	private OrderList orderlist = new OrderList();
	private AccountBook accounting = new AccountBook();
	private LoyaltyCardList customercards = new LoyaltyCardList();
	private CustomerList customers = new CustomerList();
	private SaleTransactionList sales = new SaleTransactionList();
	private CreditCardList creditCards = new CreditCardList();
	private ReturnTransactionList returnTransactions = new ReturnTransactionList();
	private List<Product> products = this.init();

	private List<Product> init() {
		List<Product> products = SQLiteJDBC.init("Products", Product.class);
		if (products == null)
			products = new ArrayList<Product>();
		return products;
	}
	
	private boolean productUpdate(Product p) {
		return SQLiteJDBC.update("Products", p.getRfID(), p.getpID(), p.getStatus(), p.getSaleID());
	}
	
	private void checkRFIDValidity(String rfid) throws InvalidRFIDException {
		if(rfid == null || !rfid.matches("\\d{12}"))
			throw new InvalidRFIDException();
	}
	
	private void checkAuthority(String... secondaryRole) throws UnauthorizedException {
		User usr = userlist.getAllUsers().stream().filter(u -> u.getId() == userlist.getAuthenticatedUser()).findAny()
				.orElse(null);
		boolean flag = true;
		if (usr == null) {
			throw new UnauthorizedException();
		}
		for (String r : secondaryRole)
			if (usr.getRole().equals(r))
				flag = false;
		if (flag)
			throw new UnauthorizedException();
	}

	private void checkCodeValidity(String productCode) throws InvalidProductCodeException {
		if (productCode == null || productCode.equals("")) {
			throw new InvalidProductCodeException();
		}
		boolean ret = true;
		if (!strIsNumeric(productCode)) {
			throw new InvalidProductCodeException();
		}
		Integer length = productCode.length();
		switch (length) {
		case 12:
			ret = checkGTIN(productCode, 12);
			break;

		case 13:
			ret = checkGTIN(productCode, 13);
			break;

		case 14:
			ret = checkGTIN(productCode, 14);
			break;

		default:
			ret = false;
			;
		}
		if (!ret) {
			throw new InvalidProductCodeException();
		}
	}

	private boolean checkGTIN(String productCode, Integer length) {
		Integer i, sum = 0;
		for (i = 0; i < (length - 1); i++) {
			if (i % 2 == 0) {
				sum += Integer.parseInt(String.valueOf(productCode.charAt(i))) * 3;
			} else {
				sum += Integer.parseInt(String.valueOf(productCode.charAt(i)));
			}
		}
		sum = nearest10(sum) - sum;
		if (sum == Integer.parseInt(String.valueOf(productCode.charAt(length - 1)))) {
			return true;
		}
		return false;
	}

	private Integer nearest10(Integer num) {
		Integer multiple = 10;
		while (multiple < num) {
			multiple += 10;
		}
		return multiple;
	}

	private boolean strIsNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public void reset() {
		SQLiteJDBC.reset();
		userlist = new UserList();
		productlist = new ProductTypeList();
		orderlist = new OrderList();
		accounting = new AccountBook();
		customercards = new LoyaltyCardList();
		customers = new CustomerList();
		sales = new SaleTransactionList();
		creditCards = new CreditCardList();
		returnTransactions = new ReturnTransactionList();
		products = this.init();
	}

	@Override
	public Integer createUser(String username, String password, String role)
			throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		return userlist.addUser(username, password, role);
	}

	@Override
	public boolean deleteUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
		checkAuthority("Administrator");
		return userlist.deleteUser(id);
	}

	@Override
	public List<User> getAllUsers() throws UnauthorizedException {
		checkAuthority("Administrator");
		return userlist.getAllUsers().stream().collect(Collectors.toList());
	}

	@Override
	public User getUser(Integer id) throws InvalidUserIdException, UnauthorizedException {
		checkAuthority("Administrator");
		if(id == null || id <= 0)
            throw new InvalidUserIdException();
		return userlist.getAllUsers().stream().filter(u -> u.getId() == id).findAny().orElse(null);
	}

	@Override
	public boolean updateUserRights(Integer id, String role)
			throws InvalidUserIdException, InvalidRoleException, UnauthorizedException {
		checkAuthority("Administrator");
		return userlist.updateUserRights(id, role);
	}

	@Override
	public User login(String username, String password) throws InvalidUsernameException, InvalidPasswordException {
		Integer id;
		if (userlist.setAuthenticatedUser(username, password)) {
			id = userlist.getAuthenticatedUser();
			return userlist.getAllUsers().stream().filter(u -> u.getId() == id).findAny().orElse(null);
		}
		return null;
	}

	@Override
	public boolean logout() {
		return userlist.emptyAuthenticatedUser();
	}

	@Override
	public Integer createProductType(String description, String productCode, double pricePerUnit, String note)
			throws InvalidProductDescriptionException, InvalidProductCodeException, InvalidPricePerUnitException,
			UnauthorizedException {
		checkAuthority("Administrator", "ShopManager");
		checkCodeValidity(productCode);
		return productlist.addProductType(description, productCode, pricePerUnit, note, "");
	}

	@Override
	public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
			throws InvalidProductIdException, InvalidProductDescriptionException, InvalidProductCodeException,
			InvalidPricePerUnitException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		checkCodeValidity(newCode);
		return productlist.updateProduct(id, newDescription, newCode, newPrice, newNote);
	}

	@Override
	public boolean deleteProductType(Integer id) throws InvalidProductIdException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		ProductType prod;
		prod = productlist.searchProductTypeByID(id);
		if (prod == null) {
			return false;
		}
		if (!SQLiteJDBC.delete("Inventory", prod.getId()))
			return false;
		productlist.getProductTypeList().remove(prod);
		return true;
	}

	@Override
	public List<ProductType> getAllProductTypes() throws UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		return productlist.getProductTypeList().stream().collect(Collectors.toList());
	}

	@Override
	public ProductType getProductTypeByBarCode(String barCode)
			throws InvalidProductCodeException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		checkCodeValidity(barCode);
		return productlist.searchProductTypeByBarCode(barCode);
	}

	@Override
	public List<ProductType> getProductTypesByDescription(String description) throws UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		return productlist.searchProductTypeByDescription(description).stream().collect(Collectors.toList());
	}

	@Override
	public boolean updateQuantity(Integer productId, int toBeAdded)
			throws InvalidProductIdException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		ProductType pt = productlist.searchProductTypeByID(productId);
		if (pt == null) {
			return false;
		}
		if ((pt.getQuantity() + toBeAdded) < 0)
			return false;
		if (!SQLiteJDBC.update("Inventory", pt.getId(), pt.getBarCode(), pt.getProductDescription(),
				pt.getPricePerUnit(), pt.getQuantity() + toBeAdded, pt.getNote(), pt.getLocation()))
			return false;
		pt.setQuantity(pt.getQuantity() + toBeAdded);
		return true;
	}

	@Override
	public boolean updatePosition(Integer productId, String newPos)
			throws InvalidProductIdException, InvalidLocationException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		return productlist.updatePosition(productId, newPos);
	}

	@Override
	public Integer issueOrder(String productCode, int quantity, double pricePerUnit) throws InvalidProductCodeException,
			InvalidQuantityException, InvalidPricePerUnitException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		checkCodeValidity(productCode);
		if(quantity<=0) { throw new InvalidQuantityException();}
		if(pricePerUnit<=0) { throw new InvalidPricePerUnitException();}
		it.polito.ezshop.model.ProductType p = this.productlist.searchProductTypeByBarCode(productCode);
		if (p==null) return -1;
		return orderlist.addOrder(pricePerUnit * quantity, productCode, pricePerUnit, quantity, "ISSUED");
	}

	@Override
	public Integer payOrderFor(String productCode, int quantity, double pricePerUnit)
			throws InvalidProductCodeException, InvalidQuantityException, InvalidPricePerUnitException,
			UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		checkCodeValidity(productCode);
		issueOrder(productCode, quantity, pricePerUnit);
		it.polito.ezshop.model.Order o = orderlist.searchOrderByFields(productCode, quantity, pricePerUnit);
		if(o==null) {
			return -1;
		}
		if (!o.getStatus().equalsIgnoreCase("ISSUED")) {
			return -1;
		}
		double ppu = o.getPricePerUnit();
		Integer qta = o.getQuantity();
		if (qta * ppu > accounting.getBalance()) {
			return -1;
		}
		if (!accounting.addBalanceOperation(o))
			return -1;
		if (!SQLiteJDBC.update("Orders", o.getBalanceId(), o.getMoney(), o.getDate().toString(), o.getProductCode(),
				o.getPricePerUnit(), o.getQuantity(), "PAYED", o.getOrderId()))
			return -1;
		o.setStatus("PAYED");
		return o.getOrderId();
	}

	@Override
	public boolean payOrder(Integer orderId) throws InvalidOrderIdException, UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		it.polito.ezshop.model.Order o = orderlist.searchOrderByID(orderId);
		if(o==null) {
			return false;
		}
		if (!o.getStatus().equalsIgnoreCase("ISSUED")) {
			return false;
		}
		double ppu = o.getPricePerUnit();
		Integer qta = o.getQuantity();
		if (qta * ppu > accounting.getBalance()) {
			return false;
		}
		if (!accounting.addBalanceOperation(o))
			return false;
		if (!SQLiteJDBC.update("Orders", o.getBalanceId(), o.getMoney(), o.getDate().toString(), o.getProductCode(),
				o.getPricePerUnit(), o.getQuantity(), "PAYED", o.getOrderId()))
			return false;
		o.setStatus("PAYED");
		return true;
	}

	@Override
	public boolean recordOrderArrival(Integer orderId)
			throws InvalidOrderIdException, UnauthorizedException, InvalidLocationException {
		checkAuthority("ShopManager", "Administrator");
		it.polito.ezshop.model.Order o = orderlist.searchOrderByID(orderId);
		if (o==null || !(o.getStatus().equalsIgnoreCase("payed") || o.getStatus().equalsIgnoreCase("completed"))) {
			return false;
		}
		if (o.getStatus().equalsIgnoreCase("completed")) {
			return true;
		}
		ProductType p;
		try {
			p = productlist.searchProductTypeByBarCode(o.getProductCode());
		} catch (InvalidProductCodeException e) {
			e.printStackTrace();
			return false;
		}
		if(p==null) {
			return false;
		}
		if (p.getLocation() == null || p.getLocation().equals("")) {
			throw new InvalidLocationException();
		}
		if(!SQLiteJDBC.update("Orders", o.getBalanceId(), o.getMoney(), o.getDate().toString(), o.getProductCode(),
				o.getPricePerUnit(), o.getQuantity(), "COMPLETED", o.getOrderId()))
			return false;
		o.setStatus("COMPLETED");
		if(!SQLiteJDBC.update("Inventory", p.getId(), p.getBarCode(), p.getProductDescription(), p.getPricePerUnit(),
				p.getQuantity() + o.getQuantity(), p.getNote(), p.getLocation()))
			return false;
		p.setQuantity(p.getQuantity() + o.getQuantity());
		return true;
	}

	@Override
	public List<Order> getAllOrders() throws UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		return orderlist.getAllOrders().stream().collect(Collectors.toList());
	}

	@Override
	public Integer defineCustomer(String customerName) throws InvalidCustomerNameException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return this.customers.addCustomer(customerName);
	}

	@Override
	public boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard)
			throws InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException,
			UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if (id == null || id <= 0) throw new InvalidCustomerIdException();
		if (newCustomerName == null || newCustomerName.equals("")) throw new InvalidCustomerNameException();
		if(newCustomerCard != null && !newCustomerCard.equals("")) {
			this.customercards.searchLoyaltyCardById(newCustomerCard);
		}
		if (customers.getCustomer(id) == null)
			return false;
		if (newCustomerCard != null && !newCustomerCard.equals("")) {
			if (customers.getCustomerList().stream().filter(c -> newCustomerCard.equals(c.getCustomerCard())).findAny()
					.isPresent())
				return false;
			if (!customercards.addLoyaltyCard(newCustomerCard))
				return false;
		}
		if (newCustomerCard != null && newCustomerCard.equals("")) {
			String oldCard = customers.getCustomer(id).getCustomerCard(); 
			if (!(oldCard == null || oldCard.equals("")) && !customercards.deleteLoyaltyCard(customers.getCustomer(id).getCustomerCard()))
				return false;
		}
		customers.updateCustomer(id, newCustomerName, newCustomerCard);
		return true;
	}

	@Override
	public boolean deleteCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return this.customers.deleteCustomer(id);
	}

	@Override
	public Customer getCustomer(Integer id) throws InvalidCustomerIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return this.customers.getCustomer(id);
	}

	@Override
	public List<Customer> getAllCustomers() throws UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return this.customers.getCustomerList().stream().collect(Collectors.toList());
	}

	@Override
	public String createCard() throws UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return customercards.addLoyaltyCard();
	}

	@Override
	public boolean attachCardToCustomer(String customerCard, Integer customerId)
			throws InvalidCustomerIdException, InvalidCustomerCardException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if (customerId == null || customerId<=0) throw new InvalidCustomerIdException();
		LoyaltyCard lc = customercards.searchLoyaltyCardById(customerCard);
		if (lc == null)
			return false;
		if ((customers.getCustomerList().stream().filter(c -> customerCard.equals(c.getCustomerCard())).findAny()
				.orElse(null)) != null) {
			return false;
		}
		Customer c = customers.getCustomer(customerId);
		if (!SQLiteJDBC.update("Customers", c.getId(), c.getCustomerName(), lc.getCardID(), lc.getPoints()))
			return false;
		customers.attachCustomerCard(customerId, lc.getCardID());
		c.setPoints(lc.getPoints());
		return true;
	}

	@Override
	public boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded)
			throws InvalidCustomerCardException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if (!customercards.updatePointsOnCard(customerCard, pointsToBeAdded)) {
			return false;
		}
		Customer customer = customers.getCustomerList().stream().filter(c -> customerCard.equals(c.getCustomerCard()))
				.findAny().orElse(null);
		if (customer != null) {
			if (!SQLiteJDBC.update("Customers", customer.getId(), customer.getCustomerName(), customerCard,
					customer.getPoints() + pointsToBeAdded))
				return false;
			customer.setPoints(customercards.searchLoyaltyCardById(customerCard).getPoints() + pointsToBeAdded);
			return true;
		}
		return false;
	}

	@Override
	public Integer startSaleTransaction() throws UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		return sales.addSale();
	}

	@Override
	public boolean addProductToSale(Integer transactionId, String productCode, int amount)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		checkCodeValidity(productCode);
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		if (amount < 0)
			throw new InvalidQuantityException();
		ProductType p = productlist.searchProductTypeByBarCode(productCode);
		if (p == null || (p.getQuantity() - amount) < 0)
			return false;
		if (sale == null || !sale.getState().equals("OPENED"))
			return false;
		p.setQuantity(p.getQuantity() - amount);
		SQLiteJDBC.update("Inventory", p.getId(), p.getBarCode(), p.getProductDescription(), p.getPricePerUnit(),
				p.getQuantity(), p.getNote(), p.getLocation());
		sale.addProduct(productCode, p.getProductDescription(), p.getPricePerUnit(), amount);
		return true;
	}

	@Override
	public boolean deleteProductFromSale(Integer transactionId, String productCode, int amount)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidQuantityException,
			UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		checkCodeValidity(productCode);
		if (amount < 0)
			throw new InvalidQuantityException();
		ProductType p = productlist.searchProductTypeByBarCode(productCode);
		if (p == null)
			return false;
		if (sale == null || !sale.getState().equals("OPENED") || !sale.deleteProduct(productCode, amount))
			return false;
		p.setQuantity(p.getQuantity() + amount);
		SQLiteJDBC.update("Inventory", p.getId(), p.getBarCode(), p.getProductDescription(), p.getPricePerUnit(),
				p.getQuantity(), p.getNote(), p.getLocation());
		return true;
	}

	@Override
	public boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate)
			throws InvalidTransactionIdException, InvalidProductCodeException, InvalidDiscountRateException,
			UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		checkCodeValidity(productCode);
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		if (discountRate < 0.0 || discountRate >= 1.0)
			throw new InvalidDiscountRateException();
		ProductType p = productlist.searchProductTypeByBarCode(productCode);
		if (p == null)
			return false;
		if (sale == null || !sale.getState().equals("OPENED") || !sale.setDiscountToProduct(productCode, discountRate))
			return false;
		return true;
	}

	@Override
	public boolean applyDiscountRateToSale(Integer transactionId, double discountRate)
			throws InvalidTransactionIdException, InvalidDiscountRateException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if (discountRate < 0.0 || discountRate >= 1.0)
			throw new InvalidDiscountRateException();
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		if (sale == null || sale.getState().equals("PAYED"))
			return false;
		if (!SQLiteJDBC.update("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
				sale.getTicketNumber(), sale.getEntriesToString(), sale.getState(), sale.getPaymentType(),
				sale.getDiscountRate()))
			return false;
		sale.setDiscountRate(discountRate);
		return true;
	}

	@Override
	public int computePointsForSale(Integer transactionId) throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		if (sale == null)
			return -1;
		return (int) sale.getMoney() / 10;
	}

	@Override
	public boolean endSaleTransaction(Integer transactionId)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if(!sales.closeSale(transactionId))
			return false;
		products.stream().filter(p -> (p.getSaleID() == transactionId && p.getStatus().equals("ONSALE"))).forEach(p -> { p.setStatus("SOLD"); this.productUpdate(p);});
		return true;
	}

	@Override
	public boolean deleteSaleTransaction(Integer saleNumber)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		SaleTransaction sale = sales.searchSale(saleNumber);
		if (!sales.deleteSale(saleNumber))
			return false;
		sale.getEntries().stream().forEach(t -> {
			try {
				ProductType p = productlist.searchProductTypeByBarCode(t.getBarCode());
				p.setQuantity(p.getQuantity() + t.getAmount());
			} catch (InvalidProductCodeException e) {
				e.printStackTrace();
			}
		});
		return true;
	}

	@Override
	public SaleTransaction getSaleTransaction(Integer transactionId)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
		if (sale == null || !sale.getState().equals("CLOSED"))
			return null;
		return sale;
	}

	@Override
	public Integer startReturnTransaction(Integer saleNumber)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(saleNumber);
		if(sale == null || !sale.getState().equals("PAYED"))
			return -1;
		return returnTransactions.addReturn(saleNumber);
	}

	@Override
	public boolean returnProduct(Integer returnId, String productCode, int amount) throws InvalidTransactionIdException,
			InvalidProductCodeException, InvalidQuantityException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);
		checkCodeValidity(productCode);
		ProductType prod = productlist.searchProductTypeByBarCode(productCode);
		if (amount <= 0)
			throw new InvalidQuantityException();
		if (prod == null) {
			return false;
		}
		double ppu = prod.getPricePerUnit();
		if (rt != null) {
			it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(rt.getSaleTransactionID());
			if(!sale.getState().equals("PAYED"))
				return false;
			TicketEntry entry = sale.getEntries().stream().filter(e -> e.getBarCode().equals(productCode)).findAny().orElse(null);
			if(entry == null || entry.getAmount() < amount)
				return false;
			rt.setProductCode(productCode);
			rt.setMoney(amount*ppu);
			rt.setQuantity(amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean endReturnTransaction(Integer returnId, boolean commit)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);
		if (rt != null) {
			String status = rt.getStatus();
			if (status.compareToIgnoreCase("OPENED") == 0) {
				if (commit == true) {
					try {
						it.polito.ezshop.model.ProductType prod = productlist
								.searchProductTypeByBarCode(rt.getProductCode());
						it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(rt.getSaleTransactionID());
						if(!sale.deleteProduct(rt.getProductCode(), rt.getQuantity()))
							return false;
						if(!SQLiteJDBC.update("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
								sale.getTicketNumber(), sale.getEntriesToString(), sale.getState(), sale.getPaymentType(),
								sale.getDiscountRate()))
							return false;
						if(!this.updateQuantity(prod.getId(), rt.getQuantity()))
							return false;
						if (!returnTransactions.closeReturnTransaction(returnId))
							return false;
						products.stream().filter(p -> (p.getSaleID() == rt.getSaleTransactionID() && p.getStatus().equals("ONRETURN"))).forEach(p -> { p.setStatus("AVAILABLE"); this.productUpdate(p);});
						return true;
					} catch (InvalidProductCodeException | InvalidProductIdException e) {
						e.printStackTrace();
					}
				} else {
					if(!returnTransactions.deleteReturnTransaction(returnId))
						return false;
					products.stream().filter(p -> (p.getSaleID() == rt.getSaleTransactionID() && p.getStatus().equals("ONRETURN"))).forEach(p -> { p.setStatus("SOLD"); this.productUpdate(p);});	
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean deleteReturnTransaction(Integer returnId)
			throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);

		if (rt != null) {
			try {
				it.polito.ezshop.model.ProductType prod = productlist
						.searchProductTypeByBarCode(rt.getProductCode());
				if(!this.updateQuantity(prod.getId(), rt.getQuantity()))
					return false;
			} catch (InvalidProductCodeException | InvalidProductIdException e) {
				e.printStackTrace();
				return false;
			}
			it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(rt.getSaleTransactionID());
			for (it.polito.ezshop.data.TicketEntry te : sale.getEntries()) {
				if (te.getBarCode().equals(rt.getProductCode())) {
					int amt = te.getAmount();
					te.setAmount(amt - rt.getQuantity());
					if(!SQLiteJDBC.update("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
							sale.getTicketNumber(), sale.getEntriesToString(), sale.getState(), sale.getPaymentType(),
							sale.getDiscountRate()))
						return false;
					returnTransactions.deleteReturnTransaction(returnId);
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public double receiveCashPayment(Integer ticketNumber, double cash)
			throws InvalidTransactionIdException, InvalidPaymentException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		if (cash <= 0) {
			throw new InvalidPaymentException();
		}
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(ticketNumber);
		if (sale == null) {
			return -1;
		}
		if (sale.getPrice() <= cash) {
			if (!accounting.addBalanceOperation(sale))
				return -1;
			if (!SQLiteJDBC.update("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
					sale.getTicketNumber(), sale.getEntriesToString(), "PAYED", sale.getPaymentType(),
					sale.getDiscountRate()))
				return -1;
			sale.setState("PAYED");
			return cash - sale.getPrice();
		}
		return -1;
	}

	@Override
	public boolean receiveCreditCardPayment(Integer ticketNumber, String creditCard)
			throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		//add a fake checkBalance, in order to be able to pass testcase (official) 'testReceiveNullCreditCardPayment'
		creditCards.checkBalance(creditCard, 0.0);
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(ticketNumber);
		if(sale == null) {
			return false;
		}
		if (creditCards.checkBalance(creditCard, sale.getPrice())) {
			if (!accounting.addBalanceOperation(sale))
				return false;
			if (!SQLiteJDBC.update("SaleTransactions", sale.getBalanceId(), sale.getMoney(), sale.getDate().toString(),
					sale.getTicketNumber(), sale.getEntriesToString(), "PAYED", sale.getPaymentType(),
					sale.getDiscountRate()))
				return false;
			sale.setState("PAYED");
			return true;
		}

		return false;
	}

	@Override
	public double returnCashPayment(Integer returnId) throws InvalidTransactionIdException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		it.polito.ezshop.model.ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);
		if (rt != null && rt.getStatus().equalsIgnoreCase("CLOSED")) {
			double price = rt.getMoney();
			if (!accounting.addBalanceOperation(rt))
				return -1;
			return price;
		}

		return -1;
	}

	@Override
	public double returnCreditCardPayment(Integer returnId, String creditCard)
			throws InvalidTransactionIdException, InvalidCreditCardException, UnauthorizedException {
		checkAuthority("Cashier", "ShopManager", "Administrator");
		CreditCard card = creditCards.searchCardByCode(creditCard);
		it.polito.ezshop.model.ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);
		if (rt != null && rt.getStatus().equalsIgnoreCase("CLOSED")) {
			double price = rt.getMoney();
			if (!accounting.addBalanceOperation(rt))
				return -1;
			card.setBalance(card.getBalance() + price);
			SQLiteJDBC.update("CreditCards", card.getBalance());
			return price;
		}
		return -1;
	}

	@Override
	public boolean recordBalanceUpdate(double toBeAdded) throws UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		if (toBeAdded + this.computeBalance() < 0) {
			return false;
		}
		it.polito.ezshop.model.BalanceOperation b;
		if (toBeAdded >= 0) {
			b = new it.polito.ezshop.model.BalanceOperation("CREDIT", toBeAdded);
		} else {
			b = new it.polito.ezshop.model.BalanceOperation("DEBIT", -toBeAdded);
		}
		return this.accounting.addBalanceOperation(b);
	}

	@Override
	public List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to) throws UnauthorizedException {
		checkAuthority("ShopManager", "Administrator");
		if (from != null && to != null) {
			if (from.compareTo(to) > 0) {
				LocalDate tmp = from;
				from = to;
				to = tmp;
			}
		}
		List<it.polito.ezshop.model.BalanceOperation> acc = this.accounting.getAccountingList();
		List<it.polito.ezshop.model.BalanceOperation> res = new ArrayList<it.polito.ezshop.model.BalanceOperation>();
		for (it.polito.ezshop.model.BalanceOperation b : acc) {
			if ((from == null || b.getDate().compareTo(from) >= 0) && (to == null || b.getDate().compareTo(to) <= 0)) {
				res.add(b);
			}
		}
		return res.stream().collect(Collectors.toList());
	}

	@Override
	public double computeBalance() throws UnauthorizedException {
		checkAuthority("Cashier" ,"ShopManager", "Administrator");
		return this.accounting.getBalance();
	}

	

    @Override
    public boolean recordOrderArrivalRFID(Integer orderId, String RFIDfrom) throws InvalidOrderIdException, UnauthorizedException, 
InvalidLocationException, InvalidRFIDException {
    	
    	checkAuthority("ShopManager", "Administrator");
    	checkRFIDValidity(RFIDfrom);
    	List<String> rfIdList = new ArrayList<String>();
    	ProductType pt = null;
    	Product p = null;
		it.polito.ezshop.model.Order o = orderlist.searchOrderByID(orderId);
		if (o==null || !(o.getStatus().equals("PAYED") || o.getStatus().equals("COMPLETED")))
			return false;
		if(o.getStatus().equals("COMPLETED"))
			return true;
		try {
			pt = productlist.searchProductTypeByBarCode(o.getProductCode());
		} catch (InvalidProductCodeException e) {
			e.printStackTrace();
		}
		if( pt == null)
			return false;
		if (pt.getLocation() == null || pt.getLocation().equals(""))
			throw new InvalidLocationException();	
		
		
		Integer counter = 0;
		Integer quantity = o.getQuantity();
		String base = "000000000000";
		Long tmp = Long.parseLong(RFIDfrom);
		Long maxValue = 1000000000000L;
		
		do {
			String rfID = base;
			rfID = rfID.substring(tmp.toString().length()) + tmp.toString();
			rfIdList.add(rfID);
			tmp = (tmp + 1)%maxValue;
			final String rfIDFinal = rfID;
			if(products.stream().filter(prod -> prod.getRfID().equals(rfIDFinal)).findAny().isPresent())
	    		 throw new InvalidRFIDException();
		} while(++counter < quantity);
		
		for(String s : rfIdList) {
			p = new Product(s, pt.getId(), -1);
			if(!SQLiteJDBC.insert("Products", p.getRfID(), p.getpID(), p.getStatus(), p.getSaleID()))
				return false;
			products.add(p);
		}
		if(!SQLiteJDBC.update("Orders", o.getBalanceId(), o.getMoney(), o.getDate().toString(), o.getProductCode(),
				o.getPricePerUnit(), o.getQuantity(), "COMPLETED", o.getOrderId()))
			return false;
		o.setStatus("COMPLETED");
		if(!SQLiteJDBC.update("Inventory", pt.getId(), pt.getBarCode(), pt.getProductDescription(), pt.getPricePerUnit(),
				pt.getQuantity() + o.getQuantity(), pt.getNote(), pt.getLocation()))
			return false;
		pt.setQuantity(pt.getQuantity() + o.getQuantity());
        return true;
    }
    

    @Override
    public boolean addProductToSaleRFID(Integer transactionId, String RFID) throws InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException {
        checkAuthority("Administrator", "ShopManager", "Cashier");
    	checkRFIDValidity(RFID);
        Product p = products.stream().filter(pr -> pr.getRfID().equals(RFID)).findAny().orElse(null);
        if(p == null || !p.getStatus().equals("AVAILABLE"))
        	return false;
        p.setStatus("ONSALE");
        p.setSaleID(transactionId);
        try {
			if(!this.addProductToSale(transactionId, productlist.searchProductTypeByID(p.getpID()).getBarCode(), 1)) {
				p.setStatus("AVAILABLE");
				p.setSaleID(null);
				return false;
			}
		} catch (InvalidProductCodeException | InvalidProductIdException e) {
			e.printStackTrace();
			return false;
		}
        if(!this.productUpdate(p)) {
        	p.setStatus("AVAILABLE");
			p.setSaleID(null);
			return false;
        }
    	return true;
    }
    
    

    @Override
    public boolean deleteProductFromSaleRFID(Integer transactionId, String RFID) throws InvalidTransactionIdException, InvalidRFIDException, InvalidQuantityException, UnauthorizedException{
		checkAuthority("Cashier", "ShopManager", "Administrator");
		checkRFIDValidity(RFID);
		it.polito.ezshop.model.SaleTransaction sale = sales.searchSale(transactionId);
        Product p = products.stream().filter(pr -> pr.getRfID().equals(RFID)).findAny().orElse(null); 
		if(p == null || !p.getStatus().equals("ONSALE") || p.getSaleID()!=transactionId)
			return false;
		p.setStatus("AVAILABLE");	
		p.setSaleID(transactionId);
		try{
			if(!this.deleteProductFromSale(transactionId, productlist.searchProductTypeByID(p.getpID()).getBarCode(), 1)) {
        		p.setStatus("ONSALE");
        		p.setSaleID(transactionId);
				return false;
			}
		} catch (InvalidProductCodeException | InvalidProductIdException e) {
			e.printStackTrace();
			return false;
		}
        if(!this.productUpdate(p)) {
        	p.setStatus("ONSALE");
			p.setSaleID(transactionId);
			return false;
        }
        return true;
    }

    
 
    @Override
    public boolean returnProductRFID(Integer returnId, String RFID) throws InvalidTransactionIdException, InvalidRFIDException, UnauthorizedException {
    	checkAuthority("Administrator", "ShopManager", "Cashier");
    	ReturnTransaction rt = returnTransactions.searchReturnTransaction(returnId);
    	checkRFIDValidity(RFID);
        Product p = products.stream().filter(pr -> pr.getRfID().equals(RFID)).findAny().orElse(null);
        if( rt == null || p == null || !p.getStatus().equals("SOLD"))
        	return false;
        if(rt.getSaleTransactionID() != p.getSaleID())
        	return false;
        p.setStatus("ONRETURN");
        try {
			if(!this.returnProduct(returnId, productlist.searchProductTypeByID(p.getpID()).getBarCode(), 1)) {
				p.setStatus("ONSALE");
				return false;
			}
		} catch ( InvalidProductCodeException | InvalidQuantityException | InvalidProductIdException e) {
			e.printStackTrace();
			return false;
		}
        if(!productUpdate(p)) {
			p.setStatus("ONSALE");
			return false;
        }
        return true;
    }


    
}
