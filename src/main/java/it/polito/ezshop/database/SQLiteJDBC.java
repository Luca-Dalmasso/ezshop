/* LOG
 * fare update
 * funzione per stablire il max id per balance op
 * */

package it.polito.ezshop.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {

	private static Connection connect() {
		String path = "jdbc:sqlite:src/main/java/it/polito/ezshop/database/EZshop.sqlite";
		Connection c = null;
		try {
			c = DriverManager.getConnection(path);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

	public static Integer maxID() {

		String sql = "SELECT MAX(id) as maxID FROM (SELECT transactionID as id FROM Accounting UNION SELECT saleID as id FROM SaleTransactions UNION SELECT returnID as id FROM ReturnTransactions UNION SELECT orderID as id FROM Orders)";
		Integer maxID = null;

		try {
			Connection c = SQLiteJDBC.connect();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(!rs.next())
				return 0;
			maxID = rs.getInt("maxID");
			c.close();
			return maxID;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return maxID;
		}
	}

	public static boolean insert(String tableName, Object... tuple) {

		String sql = null;

		try {
			Connection c = SQLiteJDBC.connect();
			if (tableName == "Customers" && tuple.length == 4) {
				sql = "INSERT INTO Customers (customerID,name,customerCard,points) VALUES(?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setString(2, (String) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setInt(4, (Integer) tuple[3]);
				pstmt.executeUpdate();
			} else if (tableName == "Inventory" && tuple.length == 7) {
				sql = "INSERT INTO Inventory (productID,barcode,description,sellPrice,quantity,note,position) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setString(2, (String) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setDouble(4, (Double) tuple[3]);
				pstmt.setInt(5, (Integer) tuple[4]);
				pstmt.setString(6, (String) tuple[5]);
				pstmt.setString(7, (String) tuple[6]);
				pstmt.executeUpdate();
			} else if (tableName == "Users" && tuple.length == 4) {
				sql = "INSERT INTO Users (userID,username,password,role) VALUES(?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setString(2, (String) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setString(4, (String) tuple[3]);
				pstmt.executeUpdate();
			} else if (tableName == "Accounting" && tuple.length == 4) {
				sql = "INSERT INTO Accounting (transactionID,type,amount,date) VALUES(?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setString(2, (String) tuple[1]);
				pstmt.setDouble(3, (Double) tuple[2]);
				pstmt.setString(4, (String) tuple[3]);
				pstmt.executeUpdate();
			} else if (tableName == "SaleTransactions" && tuple.length == 8) {
				sql = "INSERT INTO SaleTransactions (saleID,amount,date,ticketNumber,entries,state,paymentType,discount) VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setDouble(2, (Double) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setInt(4, (Integer) tuple[3]);
				pstmt.setString(5, (String) tuple[4]);
				pstmt.setString(6, (String) tuple[5]);
				pstmt.setString(7, (String) tuple[6]);
				pstmt.setDouble(8, (Double) tuple[7]);
				pstmt.executeUpdate();
			} else if (tableName == "ReturnTransactions" && tuple.length == 7) {
				sql = "INSERT INTO ReturnTransactions (returnID,amount,date,status,barCode,quantity,transactionID) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setDouble(2, (Double) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setString(4, (String) tuple[3]);
				pstmt.setString(5, (String) tuple[4]);
				pstmt.setInt(6, (Integer) tuple[5]);
				pstmt.setInt(7, (Integer) tuple[6]);
				pstmt.executeUpdate();
			} else if (tableName == "Orders" && tuple.length == 8) {
				sql = "INSERT INTO Orders (orderID,amount,date,barCode,purchasePrice,quantity,status,ID) VALUES(?,?,?,?,?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[0]);
				pstmt.setDouble(2, (Double) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setString(4, (String) tuple[3]);
				pstmt.setDouble(5, (Double) tuple[4]);
				pstmt.setInt(6, (Integer) tuple[5]);
				pstmt.setString(7, (String) tuple[6]);
				pstmt.setInt(8, (Integer) tuple[7]);
				pstmt.executeUpdate();
			} else if (tableName == "LoyaltyCards" && tuple.length == 2) {
				sql = "INSERT INTO LoyaltyCards (cardID,points) VALUES(?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[0]);
				pstmt.setInt(2, (Integer) tuple[1]);
				pstmt.executeUpdate();
			} else if (tableName == "Products" && tuple.length == 4) {
				sql = "INSERT INTO Products (RFID,productID,status,saleID) VALUES(?,?,?,?)";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[0]);
				pstmt.setInt(2, (Integer) tuple[1]);
				pstmt.setString(3, (String) tuple[2]);
				pstmt.setInt(4, (Integer) tuple[3]);
				pstmt.executeUpdate();
			} else {
				return false;
			}
			c.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean update(String tableName, Object... tuple) {

		String sql = null;

		try {
			Connection c = SQLiteJDBC.connect();
			if (tableName == "Customers" && tuple.length == 4) {
				sql = "UPDATE Customers SET name = ?, customerCard = ?, points = ? WHERE customerID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setInt(3, (Integer) tuple[3]);
				pstmt.setInt(4, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "Inventory" && tuple.length == 7) {
				sql = "UPDATE Inventory SET barcode = ?,description = ?,sellPrice = ?,quantity = ?,note = ?,position = ? WHERE productID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setDouble(3, (Double) tuple[3]);
				pstmt.setInt(4, (Integer) tuple[4]);
				pstmt.setString(5, (String) tuple[5]);
				pstmt.setString(6, (String) tuple[6]);
				pstmt.setInt(7, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "Users" && tuple.length == 4) {
				sql = "UPDATE Users SET username = ?,password = ?,role = ? WHERE userID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setString(3, (String) tuple[3]);
				pstmt.setInt(4, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "Accounting" && tuple.length == 4) {
				sql = "UPDATE Accounting SET type = ?,amount = ?,date = ? WHERE transactionID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setString(1, (String) tuple[1]);
				pstmt.setDouble(2, (Double) tuple[2]);
				pstmt.setString(3, (String) tuple[3]);
				pstmt.setInt(4, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "SaleTransactions" && tuple.length == 8) {
				sql = "UPDATE SaleTransactions SET amount = ?,date = ?,ticketNumber = ?,entries = ?,state = ?,paymentType = ?,discount = ? WHERE saleID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setDouble(1, (Double) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setInt(3, (Integer) tuple[3]);
				pstmt.setString(4, (String) tuple[4]);
				pstmt.setString(5, (String) tuple[5]);
				pstmt.setString(6, (String) tuple[6]);
				pstmt.setDouble(7, (Double) tuple[7]);
				pstmt.setInt(8, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "ReturnTransactions" && tuple.length == 7) {
				sql = "UPDATE ReturnTransactions SET amount = ?,date = ?,status = ?,barCode = ?,quantity = ?,transactionID = ? WHERE returnID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setDouble(1, (Double) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setString(3, (String) tuple[3]);
				pstmt.setString(4, (String) tuple[4]);
				pstmt.setInt(5, (Integer) tuple[5]);
				pstmt.setInt(6, (Integer) tuple[6]);
				pstmt.setInt(7, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "Orders" && tuple.length == 8) {
				sql = "UPDATE Orders SET amount = ?,date = ?,barCode = ?,purchasePrice = ?,quantity = ?,status = ?,ID = ? WHERE orderID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setDouble(1, (Double) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setString(3, (String) tuple[3]);
				pstmt.setDouble(4, (Double) tuple[4]);
				pstmt.setInt(5, (Integer) tuple[5]);
				pstmt.setString(6, (String) tuple[6]);
				pstmt.setInt(7, (Integer) tuple[7]);
				pstmt.setInt(8, (Integer) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "CreditCards" && tuple.length == 2) {
				sql = "UPDATE CreditCards SET balance = ? WHERE creditCardCode = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setDouble(1, (Double) tuple[1]);
				pstmt.setString(2, (String) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "LoyaltyCards" && tuple.length == 2) {
				sql = "UPDATE LoyaltyCards SET points = ? WHERE cardID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[1]);
				pstmt.setString(2, (String) tuple[0]);
				pstmt.executeUpdate();
			} else if (tableName == "Products" && tuple.length == 4) {
				sql = "UPDATE Products SET productID = ?,status = ?,saleID = ? WHERE RFID = ?";
				PreparedStatement pstmt = c.prepareStatement(sql);
				pstmt.setInt(1, (Integer) tuple[1]);
				pstmt.setString(2, (String) tuple[2]);
				pstmt.setInt(3, (Integer) tuple[3]);
				pstmt.setString(4, (String) tuple[0]);
				pstmt.executeUpdate();
			} else {
				return false;
			}
			c.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean delete(String tableName, Object id) {

		String sql = null;

		try {
			Connection c = SQLiteJDBC.connect();
			if (tableName == "Customers")
				sql = "DELETE FROM Customers WHERE customerID=?";
			else if (tableName == "Inventory")
				sql = "DELETE FROM Inventory WHERE productID=?";
			else if (tableName == "Users")
				sql = "DELETE FROM Users WHERE userID=?";
			else if (tableName == "Accounting")
				sql = "DELETE FROM Accounting WHERE transactionID=?";
			else if (tableName == "SaleTransactions")
				sql = "DELETE FROM SaleTransactions WHERE saleID=?";
			else if (tableName == "ReturnTransactions")
				sql = "DELETE FROM ReturnTransactions WHERE returnID=?";
			else if (tableName == "Orders")
				sql = "DELETE FROM Orders WHERE orderID=?";
			else if (tableName == "LoyaltyCards")
				sql = "DELETE FROM LoyaltyCards WHERE cardID=?";
			else if (tableName == "Products")
				sql = "DELETE FROM Products WHERE RFID=?";
			else
				return false;
			PreparedStatement pstmt = c.prepareStatement(sql);
			if(tableName != "LoyaltyCards" && tableName != "Products")
				pstmt.setInt(1, (int) id);
			else
				pstmt.setString(1, (String) id);
			pstmt.executeUpdate();
			c.close();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean reset() {

		String[] types = { "TABLE" };
		String path = "src/main/java/it/polito/ezshop/database/creditCards.txt";

		try {
			Connection c = SQLiteJDBC.connect();
			DatabaseMetaData metaData = c.getMetaData();
			Statement stmt = c.createStatement();
			ResultSet tables = metaData.getTables(null, null, "%", types);
			c.setAutoCommit(false);
			while (tables.next()) {
				stmt.addBatch("DELETE FROM " + tables.getString("TABLE_NAME"));
			}
			stmt.executeBatch();
			c.commit();
			// load creditcard.txt into DB
			File f = new File(path);
			Scanner r = new Scanner(f);
			while (r.hasNextLine()) {
				String[] row = r.nextLine().split(";");
				if (row[0].startsWith("#") == false) {
					stmt.addBatch(
							"INSERT INTO CreditCards (creditCardCode,balance) VALUES (" + row[0] + "," + row[1] + ")");
				}
			}
			r.close();
			stmt.executeBatch();
			c.commit();
			c.close();
			return true;
		} catch (SQLException | FileNotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static String setTableNameString(String sql, String tableName) {

		String name = "table_name";

		if (tableName == "Customers")
			name = "Customers";
		else if (tableName == "Inventory")
			name = "Inventory";
		else if (tableName == "Users")
			name = "Users";
		else if (tableName == "Accounting")
			name = "Accounting";
		else if (tableName == "SaleTransactions")
			name = "SaleTransactions";
		else if (tableName == "ReturnTransactions")
			name = "ReturnTransactions";
		else if (tableName == "Orders")
			name = "Orders";
		else if (tableName == "CreditCards")
			name = "CreditCards";
		else if (tableName == "LoyaltyCards")
			name = "LoyaltyCards";
		else if (tableName == "Products")
			name = "Products";
		return sql.replace("table_name", name);
	}

	public static <T> List<T> init(String tableName, Class<T> className) {

		String sql = "SELECT * FROM table_name";
		sql = SQLiteJDBC.setTableNameString(sql, tableName);
		List<T> lst = new ArrayList<T>();

		try {
			Connection c = SQLiteJDBC.connect();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			if (tableName == "Customers") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, String.class, String.class, Integer.class)
							.newInstance(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			} else if (tableName == "Users") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, String.class, String.class, String.class)
							.newInstance(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			} else if (tableName == "Inventory") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, String.class, String.class, Double.class,
							Integer.class, String.class, String.class).newInstance(rs.getInt(1), rs.getString(2),
									rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
			} else if (tableName == "Accounting") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, String.class, Double.class, String.class)
							.newInstance(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
			} else if (tableName == "SaleTransactions") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, Double.class, String.class, Integer.class,
							String.class, String.class, String.class, Double.class).newInstance(rs.getInt(1),
									rs.getDouble(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
									rs.getString(7), rs.getDouble(8)));
			} else if (tableName == "ReturnTransactions") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, Double.class, String.class, String.class,
							String.class, Integer.class, Integer.class).newInstance(rs.getInt(1), rs.getDouble(2),
									rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7)));
			} else if (tableName == "Orders") {
				while (rs.next())
					lst.add(className.getConstructor(Integer.class, Double.class, String.class, String.class,
							Double.class, Integer.class, String.class, Integer.class).newInstance(rs.getInt(1),
									rs.getDouble(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6),
									rs.getString(7), rs.getInt(8)));
			} else if (tableName == "CreditCards") {
				while (rs.next())
					lst.add(className.getConstructor(String.class, Double.class).newInstance(rs.getString(1),
							rs.getDouble(2)));
			} else if (tableName == "LoyaltyCards") {
				while (rs.next())
					lst.add(className.getConstructor(String.class, Integer.class).newInstance(rs.getString(1),
							rs.getInt(2)));
			} else if (tableName == "Products") {
				while (rs.next())
					lst.add(className.getConstructor(String.class, Integer.class, String.class, Integer.class).newInstance(rs.getString(1),
							rs.getInt(2), rs.getString(3), rs.getInt(4)));
			} else
				lst = null;
			c.close();
			return lst;
		} catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
