<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/18/2025
  Time: 3:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Error | Server Error</title>
    <link rel="stylesheet" type="text/css" href="../css/style3.css">
</head>
<body>
<div class="access-denied-container">
    <h1 class="access-title">Server Error</h1>
    <p class="error-message">${requestScope['jakarta.servlet.error.status_code']}: ${requestScope['jakarta.servlet.error.message']}</p>
    <footer class="footer-text">Royal Bank</footer>
</div>
</body>
</html>
