<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>

<html>

<head>
  <title>Register</title>
</head>

<body>
<b>Please, enter your data to register:</b> <br/>
<b> ${errorMessage} </b> <br/>

<%--<form name="RegisterForm" method="POST" action="controller">
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
</form>--%>

<s:form method="post" modelAttribute="client" action="studentRegister">

  <label for="firstName">First Name*:</label><br/>
  <s:input path="firstName" placeholder="Enter firstName" required="required"/> <br/>
  <label for="lastName">Last Name*:</label><br/>
  <s:input path="lastName" placeholder="Enter lastName" required="required"/> <br/>
  <label for="login">Login*:</label><br/>
  <s:input path="login" placeholder="Enter login" required="required"/> <br/>
  <label for="password" >Password*:</label><br/>
  <s:input path="password" placeholder="Enter password" required="required"/> <br/> <br/>
  <input type="submit" value="Register!"/>

</s:form>

<button onclick="goBack()">Go Back</button>
<br/>
<script>
  function goBack() {
    window.history.back();
  }
</script>
(* - required)<br/><br/>
<br/>Debug info - session = ${sessionScope} <a href="logout">Logout</a> <br/>
(You can see log info also in D:\facultative.log) <br/>
</body>
</html>