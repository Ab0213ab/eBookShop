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
ebookshop.dao.BookDAO dao = new  ebookshop.dao.BookDAO();

try {
	java.util.List<ebookshop.model.Book_v1>books = dao.getBooks();
	
	if(books==null) {
		System.out.println ("no books");
		return;
	}
	for (ebookshop.model.Book_v1 book: books)
	{
		out.println ("<p>"+book.getTitle()+"</p>");
	}
}catch (Exception ex) {
	ex.printStackTrace();
}finally {
	
}
%>


</body>
</html>