<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
    }
       .form-container {
        background: #ffffff;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 300px;
    }
    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .form-container .message {
        text-align: center;
        margin-bottom: 20px;
        color: green;
    }
    .form-container .message.error {
        color: red;
    }
    .form-container input[type="text"],
    .form-container input[type="password"],
    .form-container select {
        width: 100%;
        padding: 10px;
        margin: 5px 0 15px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
    }
    .form-container input[type="radio"] {
        margin-right: 10px;
    }
    .form-container input[type="submit"] {
        width: 100%;
        background-color: #45a049;
        color: white;
        padding: 10px;
        margin-top: 10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    .form-container input[type="submit"]:hover {
        background-color: #45a049;
    }
</style>
</head>
<body>
    <div class="form-container">
        <h2>Registration</h2>
        <div class="message <%= request.getAttribute("messageType") != null ? request.getAttribute("messageType") : "" %>">
            <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
        </div>
        <form action="regForm" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" placeholder="Enter Name" required /><br>

            <label for="email">Email ID:</label>
            <input type="text" id="email" name="email" placeholder="Enter Email" required /><br>

            <label for="pass">Password:</label>
            <input type="password" id="pass" name="pass" placeholder="Enter Password" required /><br>

            <label>Gender:</label><br>
            <input type="radio" id="female" name="gender" value="Female" required />
            <label for="female">Female</label>
            <input type="radio" id="male" name="gender" value="Male" required />
            <label for="male">Male</label>
            <input type="radio" id="others" name="gender" value="Others" required />
            <label for="others">Others</label><br><br>

            <label for="city">City:</label>
            <select id="city" name="city" required>
                <option value="" disabled selected>Select City</option>
                <option value="Delhi">Delhi</option>
                <option value="Mumbai">Mumbai</option>
                <option value="Bangalore">Bangalore</option>
                <option value="Goa">Goa</option>
                <option value="Odisha">Odisha</option>
            </select><br>

            <input type="submit" value="Register" />
        </form>
    </div>
</body>
</html>