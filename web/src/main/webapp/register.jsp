<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/14/2025
  Time: 10:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank | Register</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
</head>
<body>
<div class="container form-container">
    <h1 class="bank-name">Royal Bank</h1>
    <h2 class="form-title">REGISTER</h2>

    <form method="POST" action="${pageContext.request.contextPath}/register" class="form-box">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" required>

        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>

        <label for="contact">Contact</label>
        <input type="text" id="contact" name="contact" required>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <button type="submit" class="action-button">Register</button>
    </form>
</div>
</body>
</html>
