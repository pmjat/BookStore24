<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book-Entry</title>
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
    <h2>Book - Edit</h2>
    <form method = "post" action="Controller">
    <input type="hidden" name="action" value="update" />
    <table class="gridtable">
        <tr><td>ISBN</td><td><input type="text" name="isbn" value="${book.isbn}" readonly /></td></tr>
        <tr><td>Title</td><td><input type="text" name="title" value="${book.title}" size="50"/></td></tr>
        <tr><td>Price</td><td><input type="text" name="price" value="${book.price}" /></td></tr>
        <tr><td>Stock</td><td><input type="text" name="stock" value="${book.stock}" /></td></tr>
        <tr><td></td><td><input type="submit" value="Update" /></td></tr>
    </table>
    </form>
    </body>
</html>
