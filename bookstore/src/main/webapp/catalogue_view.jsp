<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="main.css">
	<title>Books in Stock</title>
</head>
<body>
    <h3>Books in Stock</h3>
    <c:set var="rpath" value="http://localhost:8080${pageContext.request.contextPath}" />
    <a href="${rpath}/Controller?action=newbook"> Add New Book </a>
    <table class="gridtable">
    <tr><th>ISBN</th><th width="300">Title</th><th>Price</th><th>Stock</th><th></th><th></th></tr>
	<c:choose>
	<c:when test="${not empty requestScope.stock}">
		<c:forEach var="book" items="${stock}" >
		<tr>
		    <td><a href="${rpath}/Controller?action=showbook&isbn=${book.isbn}">${book.isbn}</a></td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>${book.stock}</td>
			<td><a href="${rpath}/Controller?action=editbook&isbn=${book.isbn}">Edit</a></td>
			<td><a href="${rpath}/Controller?action=remove&isbn=${book.isbn}">Remove</a></td>
		</tr>                			    
		</c:forEach>		
	</c:when>
	<c:otherwise>
		<tr><td colspan="4">No Books Found</td></tr>
	</c:otherwise>
	</c:choose>
    </table>
</body>
</html>
