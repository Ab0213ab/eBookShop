<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
java.util.List<ebookshop.model.Book_v1>books = (java.util.List<ebookshop.model.Book_v1>)session.getValue("books");
	for (ebookshop.model.Book_v1 book: books)
	{
		out.println ("<p>"+book.getTitle()+"</p>");
	}
%>


</body>
</html>