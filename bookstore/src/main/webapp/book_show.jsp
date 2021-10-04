<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book-Show</title>
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
    <h2>Book-Details</h2>
    <table class="gridtable">
        <tr><td>ISBN</td><td>${book.isbn}</td></tr>
        <tr><td>Title</td><td>${book.title}</td></tr>
        <tr><td>Price</td><td>${book.price}</td></tr>
        <tr><td>Stock</td><td>${book.stock}</td></tr>
    </table>
    </body>
</html>
