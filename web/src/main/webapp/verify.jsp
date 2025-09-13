<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/15/2025
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank | Verification</title>
    <link rel="stylesheet" type="text/css" href="css/style1.css">
</head>
<body>

<div class="container form-container">
    <h1 class="bank-name">Royal Bank</h1>
    <h2 class="form-title">REQUEST VERIFICATION</h2>
    <form method="POST" action="${pageContext.request.contextPath}/request-verification" class="form-box">
        <label for="request-email">Email</label>
        <input type="email" id="request-email" name="email" required>
        <button type="submit" class="action-button">Request</button>
    </form>

    <hr class="divider">

    <h2 class="form-title">VERIFY ACCOUNT</h2>
    <form method="POST" action="${pageContext.request.contextPath}/verify" class="form-box">
        <label for="verify-email">Email</label>
        <input type="email" id="verify-email" name="email" required>

        <label for="code">Verification Code</label>
        <input type="text" id="code" name="verificationCode" required>

        <button type="submit" class="action-button">Verify</button>
    </form>
</div>
</body>
</html>
