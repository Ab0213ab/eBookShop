package ebookshop.controller;

import java.util.List;
import java.util.Vector;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ebookshop.model.Book;
import ebookshop.model.Order;

@WebServlet("/eshop")
public class ShoppingServlet extends HttpServlet {
	private ebookshop.dao.BookDAO dao;
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
		 dao = new ebookshop.dao.BookDAO();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ServletContext sc = req.getSession().getServletContext();
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		@SuppressWarnings("unchecked")
		Vector<Book> shoplist = (Vector<Book>) session.getAttribute("ebookshop.cart");
		String do_this = req.getParameter("do_this");

		// If it is the 1st time, initialize the list of books, which in
		// real life would be stored in a database on a disk
		if (do_this == null) {
			List<Book>blist = dao.getBooks();
			session.setAttribute("ebookshop.list", blist);
			ServletContext sc = req.getSession().getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/");
			rd.forward(req, res);
		}else if (do_this.equals("books"))
		{
				
				List<Book>books = dao.getBooks();
				session.setAttribute("books", books);
				ServletContext sc = req.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/books.jsp");
				rd.forward(req, res);
		} else {
			// If it is not the first request, it can only be a checkout request
			// or a request to manipulate the list of books being ordered
			if (do_this.equals("checkout")) {
				Order order = getOrder(req);
				order=dao.checkout(order, shoplist);
				float dollars = order.getTotalSale();
				int books = order.getTotalQuantity();
				
				req.setAttribute("dollars", dollars);
				req.setAttribute("books", books);
				ServletContext sc = req.getSession().getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/Checkout.jsp");
				rd.forward(req, res);
			} // if (..checkout..

			// Not a checkout request - manipulate the list of books
			else {
				if (do_this.equals("remove")) {
					String pos = req.getParameter("position");
					shoplist.removeElementAt(Integer.parseInt(pos));
				} else if (do_this.equals("add")) {
					boolean found = false;
					Book aBook = getBook(req);
					if (shoplist == null) { // the shopping cart is empty
						shoplist = new Vector<Book>();
						shoplist.addElement(aBook);
					} else { // Update the 3copies if the book is already there
						for (int i = 0; i < shoplist.size() && !found; i++) {
							Book b = (Book) shoplist.elementAt(i);
							if (b.getTitle().equals(aBook.getTitle())) {
								b.setQuantity(b.getQuantity() + aBook.getQuantity());
								shoplist.setElementAt(b, i);
								found = true;
							}
						} // for (i..
						if (!found) { // if it is a new book => Add it to the shoplist
							shoplist.addElement(aBook);
						}
					} // if (shoplist == null) .. else..
				} // if (..add..

				// Save the updated list of books and return to the home page
				session.setAttribute("ebookshop.cart", shoplist);
				ServletContext sc = req.getServletContext();
				RequestDispatcher rd = sc.getRequestDispatcher("/");
				rd.forward(req, res);
			} // if (..checkout..else
		} // if (do_this..
	} // doPost
	private Order getOrder(HttpServletRequest req) {
			Order order = new Order();
			order.setDeliveryName(req.getParameter("deliveryName"));
			order.setDeliveryAddress(req.getParameter("deliveryAddress"));
			order.setCreditCardName(req.getParameter("creditCardName"));
			order.setCreditCardNumber(req.getParameter("creditCardNumber"));
			order.setExpiryDate(req.getParameter("expiryDate"));
				
			return order;
	}
	private Book getBook(HttpServletRequest req) {
		String myBook = req.getParameter("book");
		int n = myBook.indexOf('$');
		int id = parseInt(myBook.charAt(0)+"",0);
		String title = myBook.substring(0, n);
		String price = myBook.substring(n + 1);
		String qty = req.getParameter("qty");
		return new Book(id,title, Float.parseFloat(price), Integer.parseInt(qty));
	} // getBook
	private static int parseInt(String val, int alt)
	{
		try {
			return Integer.parseInt(val);
		}catch (Exception e) {
			return alt;
		}
	}
}
