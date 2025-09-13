<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/12/2025
  Time: 12:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
</head>
<body>
<c:if test="${empty pageContext.request.userPrincipal}">

    <div class="container">
        <h2 class="welcome-text">Welcome to</h2>
        <h1 class="bank-name">Royal Bank</h1>

        <div class="illustration">
            <img src="img/WhatsApp Image 2025-07-20 at 15.06.39_52a11c7e.jpg" alt="Royal Bank">
        </div>

        <p class="description">
            Where security meets simplicity. Our digital banking system is designed to give you complete control over
            your finances:
            whether you're checking your balance, transferring funds or managing your account. Built with modern
            enterprise-grade technologies,
            <strong>Royal Bank</strong> ensures your transactions are safe, your data is protected and your experience
            remains seamless
            around the clock. Join us in redefining banking with trust, transparency and technology that just works.
        </p>

        <div class="button-group">
            <a href="login.jsp" class="action-button">Sign In</a>
            <a href="register.jsp" class="action-button">Register</a>
            <a href="verify.jsp" class="action-button">Verify</a>
        </div>
    </div>

</c:if>
<c:if test="${not empty pageContext.request.userPrincipal}">
    <% response.sendRedirect(request.getContextPath() + "/user/index.jsp");%>
</c:if>
</body>
</html>
