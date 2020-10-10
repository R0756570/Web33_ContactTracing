<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
<header>
<h1><span>Contact Tracing</span></h1>
<nav>
<ul>
<li><a href="Controller">Home</a></li>
<li><a href="Controller?command=Overview">Overview</a></li>
<li id="actual"><a href="Controller?command=Register">Register</a></li>
</ul>
</nav>
<h2>
Register
</h2>

</header><main>
    <c:if test="${not empty errors}">
        <div class="alert-danger">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>


    <form action="Controller?command=addPerson" method="post" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p>
            <label for="userid">User id</label>
            <input type="text" id="userid" name="userid" value="${vorigeUserId}" required ></p>

        <p>
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" value="${vorigeFirstName}" required></p>

        <p>
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" value="${vorigeLastName}" required></p>

        <p>
            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${vorigeEmail}" required></p>

        <p>
            <label for="password">Password</label>
            <input type="password" id="password"  name="password" value="${vorigePassword}" required> </p>

        <p><input type="submit" id="signUp" value="Sign Up"></p>

    </form>
</main>
<footer>
&copy; Webontwikkeling 3, UC Leuven-Limburg
</footer>
</div>
</body>
</html>
