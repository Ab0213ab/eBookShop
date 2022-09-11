<%@ page language="java" contentType="text/html"%>
<%@page session="true" import="java.util.Vector,ebookshop.model.Book_v1" %>

<!DOCTYPE html>
<html>
<head>
<title>E-Bookshop Checkout</title>
<style type="text/css">
   body {background-color:gray; font-size=10pt;}
   H1 {font-size:20pt;}
   table {background-color:white;}
</style>
</head>
<body>
     <H1>Your Online Bookshop - Checkout</H1>
     <hr/><p/>
     <table border="1" cellpadding="2">
         <tr>
         <td>TITLE</td>
         <td align="right">PRICE</td>
         <td align="right">QUANTITY</td>
         </tr>

<%
Vector<Book_v1> shoplist =
             (Vector<Book_v1>)session.getAttribute("ebookshop.cart");
     for (Book_v1 anOrder : shoplist) {
%>
   
          <tr>
          <td><%=anOrder.getTitle()%></td>
          <td align="right">$<%=anOrder.getPrice()%></td>
          <td align="right"><%=anOrder.getQuantity()%></td>
          </tr>
<%
     }
   session.invalidate();  
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