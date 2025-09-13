<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/16/2025
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank | Transactions</title>
    <link rel="stylesheet" type="text/css" href="../css/style2.css">
</head>
<body>
<div class="container">
    <div class="navbar">
        <div class="logo">RoyalBank</div>
        <div class="nav-links">
            <a href="#">Home</a>
            <a href="#">Services</a>
            <a href="#">Help</a>
            <span class="username">Bruce Wayne</span>
        </div>
    </div>

    <div class="main-content">
        <div class="form-section">
            <h2>FUND TRANSACTION</h2>
            <form method="POST" action="${pageContext.request.contextPath}/user/fund-transfer">
                <label for="from">From</label>
                <input type="text" id="from" value="My Account" disabled>

                <label for="to">To</label>
                <input type="text" name="destinationAccountNumber" id="to" required>

                <label for="amount">Amount</label>
                <input type="number" name="amount" id="amount" required>

                <button type="submit" class="btn submit">Transfer</button>
            </form>
        </div>

        <div class="history-panel">
            <h3>Transaction History</h3>

            <div class="history-item">
                <strong>To John</strong> <span class="date">yyyy-mm-dd time</span>
                <p>Rs. 5000 amount of fund transfered to RB202503 (John)</p>
            </div>
            <div class="history-item">
                <strong>To John</strong> <span class="date">yyyy-mm-dd time</span>
                <p>Rs. 5000 amount of fund transfered to RB202503 (John)</p>
            </div>
            <div class="history-item">
                <strong>To John</strong> <span class="date">yyyy-mm-dd time</span>
                <p>Rs. 5000 amount of fund transfered to RB202503 (John)</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
