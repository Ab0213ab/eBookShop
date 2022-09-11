<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html"%>
<%@page session="true" import="java.util.Vector,ebookshop.model.Book"%>

<!DOCTYPE html>
<html>
<head>
<title>E-bookshop</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="header-image">
		<H1>Your Online Bookshop</H1>
	</div>
	<hr />
	<p />
	<img src="C:/apache-tomcat-9.0.58/webapps/images/bookWithQuill.gif" class="bookGif">

	<%
	// Check whether the booklist is ready, if not, control passed to servlet to initialize book list
	List<Book> booklist = (List<Book>) session.getValue("ebookshop.list");
	if (booklist == null) {
		response.sendRedirect("/ebookshop/eshop");
	} else {
	%>
	<form name="addForm" action="eshop" method="POST">
		<input type="hidden" name="do_this" value="add"> Book: <select
			name=book>

			<%
			// Copy the booklist to the selection control
			for (Book book : booklist) {
				out.println(String.format("<option>%s</option>", book));
			}
			%>
		</select> Quantity: <input type="number" min="1" max="100" name="qty" size="3"
			value="1"> <input type="submit" value="Add to Cart">
	</form>
	<p />

	<%
	// Check whether the shopping cart is empty
	Vector shoplist = (Vector<ebookshop.model.Book>) session.getAttribute("ebookshop.cart");
	if (shoplist != null && shoplist.size() > 0) {
	%>
	<table border="1" cellpadding="2">
		<tr>
			<td>TITLE</td>
			<td>PRICE</td>
			<td>QUANTITY</td>
			<td></td>
		</tr>

		<%
		// Display each book in the shopping cart with its own form
		// If entry deleted, request sent to servlet with do_this to remove
		for (int i = 0; i < shoplist.size(); i++) {
			Book aBook = (Book) shoplist.elementAt(i);
		%>
		<tr>
			<form name="removeForm" action="eshop" method="POST">
				<input type="hidden" name="position" value="<%=i%>"> <input
					type="hidden" name="do_this" value="remove">
				<td><%=aBook.getTitle()%></td>
				<td align="right">$<%=aBook.getPrice()%></td>
				<td align="right"><%=aBook.getId()%></td>
				<td><input type="submit" value="Remove from Cart"></td>
			</form>
		</tr>
		<%
		} // for (int i..
		%>
	</table>
	<p />
	<form name="checkoutForm" action="eshop" method="POST">
		<input type="hidden" name="do_this" value="checkout">

		<p>
			DeliveryName: <input type='text' name='deliveryName' />
		</p>
		<p>
			DeliveryAddress: <input type='text' name='deliveryAddress' />
		</p>
		<p>
			Credit Card name: <input type='text' name='creditCardName' />
		</p>
		<p>
			Credit Card number: <input type='text' name='creditCardNumber' />
		</p>
		<p>
			Expiry Date: <input type='text' name='expiryDate' />
		</p>
		<input type="submit" value="Checkout">
	</form>

	<%
	} // if (shoplist..
	} // if (booklist..else..
	%>

</body>
</html>