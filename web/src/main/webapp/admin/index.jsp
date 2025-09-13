<%--
  Created by IntelliJ IDEA.
  User: LOQ
  Date: 7/12/2025
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Royal Bank | Admin</title>
</head>
<body>
<div class="header">
    <h1>Admin Panel - Royal Bank</h1>
    <div class="user-name">Bruce Wayne</div>
</div>

<div class="main-container">
    <div class="users-section">
        <h2>Users:</h2>

        <div class="user-card">
            <div class="user-info">
                <strong>Name | AccountNumber</strong> <span class="mobile">Mobile</span>
                <p>Email: email</p>
                <p>Active Status: Active</p>
            </div>
        </div>

        <div class="user-card">
            <div class="user-info">
                <strong>Name | AccountNumber</strong> <span class="mobile">Mobile</span>
                <p>Email: email</p>
                <p>Active Status: Active</p>
            </div>
        </div>

        <div class="user-card">
            <div class="user-info">
                <strong>Name | AccountNumber</strong> <span class="mobile">Mobile</span>
                <p>Email: email</p>
                <p>Active Status: Active</p>
            </div>
        </div>

        <div class="add-admin">
            <h3>Add an Admin:</h3>
            <label for="adminEmail">Email</label>
            <input type="email" id="adminEmail" name="adminEmail" placeholder="Enter email">
            <button type="submit">Add</button>
        </div>
    </div>

    <div class="logs-section">
        <h2>User Logs:</h2>

        <div class="log-card">
            <div class="log-header">
                <strong>Action</strong> <span class="datetime">datetime</span>
            </div>
            <p>class: method</p>
            <p>message: ...............</p>
        </div>

        <div class="log-card">
            <div class="log-header">
                <strong>Action</strong> <span class="datetime">datetime</span>
            </div>
            <p>class: method</p>
            <p>message: ...............</p>
        </div>

        <div class="log-card">
            <div class="log-header">
                <strong>Action</strong> <span class="datetime">datetime</span>
            </div>
            <p>class: method</p>
            <p>message: ...............</p>
        </div>

    </div>

</div>
</body>
</html>
