<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
    <h1>Login</h1>
    <form method = "post" action="Controller">
    <table>
        <tr><td>User</td><td><input type="text" name="user" value="" /></td></tr>
        <tr><td>Password</td><td><input type="text" name="password" value="" /></td></tr>
        <tr><td></td><td><input type="submit" value="Login" /></td></tr>
    </table>
        <input type="hidden" name="action" value="loginvalidate" />
    </form>
    </body>
</html>
