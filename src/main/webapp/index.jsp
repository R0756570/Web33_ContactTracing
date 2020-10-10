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
					<li id="actual"><a href="Controller">Home</a></li>
					<li><a href="Controller?command=Overview">Overview</a></li>
					<li><a href="Controller?command=Register">Register</a></li>
				</ul>
			</nav>
			<h2>Home</h2>

		</header>
		<main>
			<c:choose>
				<c:when test="${loggedIn}">
					<p>Welcome ${person.firstName} !</p>
					<a href="Controller?command=logout&userId=${person.userid}">Logout</a>
				</c:when>
				<c:otherwise>
					<c:if test="${error != null}">
						<p class="alert-danger" >${error}</p>
					</c:if>
					<form action="Controller?command=login" method="post" novalidate="novalidate">
						<p>
							<label for="userid">User id</label>
							<input type="text" id="userid" name="userid" required ></p>
						<p>
							<label for="password">Password</label>
							<input type="password" id="password"  name="password" required> </p>
						<p><input type="submit" id="login" value="login"></p>
					</form>
				</c:otherwise>
			</c:choose>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>