<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/12/2025
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank | Home</title>
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

    <div class="balance-section">
        <span>Current Account Balance:</span>
        <strong>Rs. 2500.00</strong>
    </div>

    <div class="main-content">
        <div class="details-panel">
            <h3>Account Details:</h3>
            <p>Account No: <strong>22221584</strong></p>
            <p>Monthly interest rate: <strong>5%</strong></p>
            <p>Created at: <strong>2025-07-16 18:35:12</strong></p>
            <p>Status: <strong>Active</strong></p>

            <br>
            <hr/>
            <br>

            <h3>User Details:</h3>
            <p>Name: <strong>Bruce Wayne</strong></p>
            <p>Contact: <strong>077 123 1235</strong></p>
            <p>Status: <strong>Active</strong></p>
            <p>Role: <strong>User</strong></p>
        </div>

        <div class="history-panel">
            <h3>History:</h3>
            <div class="history-item">
                <strong>Debited</strong> <span class="date">2025-07-17 20:44:55</span>
                <p>Your account has been debited by Rs. 100.0</p>
            </div>
            <div class="history-item">
                <strong>Transaction Successful</strong> <span class="date">2025-07-17 20:44:55</span>
                <p>Your transaction to RB2025002 has been transferred by Rs. 100.0</p>
            </div>
            <div class="history-item">
                <strong>Credited</strong> <span class="date">2025-07-17 20:45:27</span>
                <p>Your account has been credited by Rs. 550.0</p>
            </div>
        </div>

        <div class="button-panel">
            <a href="fund-transfer.jsp" class="btn transfer">Fund Transfer</a>
            <a href="scheduled-transfer.jsp" class="btn schedule">Schedule Fund Transfer</a>
            <a href="${pageContext.request.contextPath}/user/download-report" class="btn report">Download Report</a>
            <a href="${pageContext.request.contextPath}/user/logout" class="btn logout">Logout</a>
        </div>
    </div>
</div>
</body>
</html>
