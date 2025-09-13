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
    <title>Royal Bank | Login</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
</head>
<body>
<div class="container">
    <h1 class="bank-name">Royal Bank</h1>
    <h2 class="form-title">LOGIN</h2>

    <form method="POST" action="${pageContext.request.contextPath}/login" class="form-box">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required/>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" required/>
        <a href="verify.jsp" class="link">Verify</a>
        <button type="submit" class="action-button">Login</button>
    </form>
</div>
</body>
</html>
