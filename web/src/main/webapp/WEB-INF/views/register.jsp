<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>

<head>
  <title>Register</title>
</head>

<body>
<b>Please, enter your data to register:</b> <br/>
<b> ${errorMessage} </b> <br/>
<form name="RegisterForm" method="POST" action="controller">
  <input type="hidden" name="command" value="studentRegister"/>
  First Name*:<br/>
  <input type="text" name="firstName" value=""/> <br/>
  Last Name*:<br/>
  <input type="text" name="lastName" value=""/> <br/>
  Login*:<br/>
  <input type="text" name="login" value=""/> <br/>
  Password*:<br/>
  <input type="password" name="password" value=""/> <br/>
  <input type="submit" value="Register!"/>
</form>
<button onclick="goBack()">Go Back</button>
<br/>
<script>
  function goBack() {
    window.history.back();
  }
</script>
(* - required)<br/><br/>
<br/>Debug info - session = ${sessionScope} <a href="controller?command=logout">Logout</a> <br/>
(You can see log info also in D:\facultative.log) <br/>
</body>
</html>