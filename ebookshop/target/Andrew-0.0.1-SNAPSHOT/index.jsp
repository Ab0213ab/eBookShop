<%@ page language="java" contentType="text/html"%>
<%@page session="true" import="java.util.Vector,ebookshop.model.Book_v1" %>

<!DOCTYPE html>
<html>
<head>
<title>E-bookshop</title>
<style type="text/css">
    body {background-color:gray; font-size=10pt;}
    H1 {font-size=20pt;}
    table {background-color:white;}
</style>
</head>
<body>
    <H1>Your Online Bookshop</H1>
    <hr/><p/>
    
<%
    // Check whether the booklist is ready
              Vector<String> booklist = 
                     (Vector<String>)session.getValue("ebookshop.list");
              if (booklist == null) {
            	  response.sendRedirect("/ebookshop/eshop");
              }
              else {
    %>
    	      <form name="addForm" action="eshop" method="POST">
    	          <input type="hidden" name="do_this" value="add">
    	          Book:
    	          <select name=book>
    	          
<%
    	          // Copy the booklist to the selection control
    	              	                for (int i = 0; i < booklist.size(); i++) {
    	              	              	  String book =  (String)booklist.elementAt(i);
    	              	              	  
    	              	              	out.println("<option>" +book +
    	              	              			"</option>");
    	              	              	}
    	          %>
       </select>
     Quantity: <input type="number" min="1" max="100" name="qty" size="3" value="1">
     <input type="submit" value="Add to Cart">
     </form>
  <p/> 
<%
 // Check whether the shopping cart is empty
       Vector shoplist = 
              (Vector<ebookshop.model.Book_v1>)session.getAttribute("ebookshop.cart");
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
// Display the books in the shopping cart
       for (int i = 0; i <shoplist.size(); i++) {
           Book_v1 aBook =(Book_v1) shoplist.elementAt(i);
%>
           <tr>
              <form name="removeForm" action="eshop" method="POST">
                 <input type="hidden" name="position" value="<%=i %>">
                 <input type="hidden" name="do_this" value="remove">
                 <td><%=aBook.getTitle()%></td>
                 <td align="right">$<%=aBook.getPrice()%></td>
                 <td align="right"><%=aBook.getQuantity()%></td>
                 <td><input type="submit" value="Remove from Cart"></td>
                 </form>
              </tr>
<%
       } // for (int i..
   %>
         </table>
         <p/>
         <form name="checkoutForm" action="eshop" method="POST">
             <input type="hidden" name="do_this" value="checkout">
             <input type="submit" value="Checkout">
             </form>
             
<%
         } // if (shoplist..
      } // if (booklist..else..
%>
 
</body>
</html>