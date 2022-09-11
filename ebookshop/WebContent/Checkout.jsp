<%@ page language="java" contentType="text/html"%>
<%@page session="true" import="java.util.Vector,ebookshop.model.Book" %>

<!DOCTYPE html>
<html>
<head>
<title>E-Bookshop Checkout</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="header-image">
		<H1>Your Online Bookshop - Checkout</H1>
	</div>
	
    <hr/>
    
    <p/>
    <img src="C:/apache-tomcat-9.0.58/webapps/images/bookWithQuill.gif" class="bookGif">
     <table border="1" cellpadding="2">
         <tr>
         <td>TITLE</td>
         <td align="right">PRICE</td>
         <td align="right">QUANTITY</td>
         </tr>

<%
Vector<Book> shoplist =
             (Vector<Book>)session.getAttribute("ebookshop.cart");
     for (Book anOrder : shoplist) {
%>
   
          <tr>
          <td><%=anOrder.getTitle()%></td>
          <td align="right">$<%=anOrder.getPrice()%></td>
          <td align="right"><%=anOrder.getQuantity()%></td>
          </tr>
<%
     }
     
%>

      <tr>
         <td>TOTALS</td>
         <td align="right">$<%=(Float)request.getAttribute("dollars")%></td>
         <td align="right"><%=(Integer)request.getAttribute("books")%></td>
         </tr>
      </table>
   <p/>
   <a href="/ebookshop/eshop">Buy More!</a>
</body>
</html>