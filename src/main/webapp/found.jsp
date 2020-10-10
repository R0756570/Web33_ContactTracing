<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Found</title>
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
        <h2>Results</h2>

    </header>
    <main>
        <p id="boodschap">more information about ${person.userid} : </p>
        <ul>
            <li> User ID: ${person.userid}</li>
            <li> E-mail: ${person.email}</li>
            <li> First Name: ${person.firstName}</li>
            <li> Last Name: ${person.lastName}</li>
        </ul>
    </main>
    <footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
</div>
</body>
</html>