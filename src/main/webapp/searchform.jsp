<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Contact Tracing</span>
        </h1>
        <nav>
            <ul>
                <li><a href="Controller">Home</a></li>
                <li><a href="Controller?command=Overview">Overview</a></li>
                <li><a href="Controller?command=Register">Register</a></li>
            </ul>
        </nav>
        <h2>Who do you want to search for ?</h2>

    </header>
    <main>
        <c:if test="${error != null}">
            <div class="alert alert-danger" id="errore">${error}</div>
        </c:if>

        <form action="Controller?command=search" method="post" novalidate="novalidate">
            <p>
                <label for="userid">User id</label>
                <input type="text" id="userid" name="userid" value="${vorigeUserId}" required ></p>

            <p><input type="submit" id="search" value="search"></p>
        </form>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>