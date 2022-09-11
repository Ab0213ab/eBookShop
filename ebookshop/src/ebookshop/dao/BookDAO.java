package ebookshop.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import ebookshop.model.Book;
import ebookshop.model.Order;

public class BookDAO {
	Database db = null;

	public static void main(String[] args) {
		BookDAO dao = new BookDAO();
		try {
				Order order = new Order();
				order.setCreditCardName("Bigger Spender");
				order.setCreditCardNumber("4123-5678-9874");
				order.setDeliveryAddress("123 Main St");
				order.setDeliveryName("John Smith");
				order.setExpiryDate("11/22");
				order.setTotalQuantity(1);
				Vector<Book>books = new Vector<Book>();
				Book book1 = new Book ();
				book1.setAuthor("Stephen King");
				book1.setCategoryId(1);
				book1.setPrice(10);
				book1.setQuantity(2);
				book1.setTitle("The Stand");
				book1.setId(1);
				books.add(book1);
				dao.checkout(order, books);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}

	public List<Book> getBooks() {
		ArrayList<Book> books = new ArrayList<Book>();
		try {
			if (db == null) {
				db = new Database();
			}
			ResultSet rs = db.getResultSet("select * from books");

			if (rs == null)
				return null;

			while (rs.next()) {
				Book book = new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), 0, rs.getInt(5));
				books.add(book);

			}
		} catch (Exception ex) {

		}finally {
			db.close();
		}

		return books;
	}

	public Order checkout(Order order, Vector<Book> shoplist) {
		int retval = 0;
		try {
			if (db == null) {
				db = new Database();
			}
				String insertIntoOrder = 
						"INSERT INTO `shop`.`orders`(`delivery_name`,`delivery_address`,`cc_name`,"
						+ "`cc_number`,`cc_expiry`)VALUES(?,?,?,?,?)";
				String insertIntoOrderDetail = "INSERT INTO `shop`.`order_details`(`book_id`,`title`,`author`,`quantity`,`price`,`order_id`)VALUES(?,?,?,?,?,?)";
				float dollars = 0;
				int books=0;
				PreparedStatement insertIntoOrderStmt = db.prepare(insertIntoOrder);
				PreparedStatement insertIntoOrderDetailStmt = db.prepare(insertIntoOrderDetail);
				insertIntoOrderStmt.setString(1,order.getDeliveryName());
				insertIntoOrderStmt.setString(2,order.getDeliveryAddress());
				insertIntoOrderStmt.setString(3,order.getCreditCardName());
				insertIntoOrderStmt.setString(4,order.getCreditCardNumber());
				insertIntoOrderStmt.setString(5,order.getExpiryDate());
				insertIntoOrderStmt.executeUpdate();
				ResultSet rs = insertIntoOrderStmt.getGeneratedKeys();
				if (rs.next())
				{
					order.setId(rs.getInt(1));
				}else {
					return null;
					
				}
				int id=order.getId();
				 for (Book book:shoplist)
				 {

					 insertIntoOrderDetailStmt.setInt(1,book.getId());
					 insertIntoOrderDetailStmt.setString(2,book.getTitle());
					 insertIntoOrderDetailStmt.setString(3,book.getAuthor());
					 insertIntoOrderDetailStmt.setInt(4,book.getQuantity());
					 insertIntoOrderDetailStmt.setFloat(5,book.getPrice());
					 insertIntoOrderDetailStmt.setInt(6,id);
					 insertIntoOrderDetailStmt.executeUpdate();
							float price = book.getPrice();
							int qty = book.getQuantity();
							dollars += price * qty;
							books += qty;
						
				 }
				 order.setTotalQuantity(books);
				 order.setTotalSale(dollars);
				
			
		} catch (Exception ex) {
ex.printStackTrace();
		}finally {
			db.close();
		}

		return order;
	}
}
