<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store v3.0</title>
    </head>
    <c:redirect url="http://localhost:8080${pageContext.request.contextPath}/Controller?action=viewcat" />
</html>
