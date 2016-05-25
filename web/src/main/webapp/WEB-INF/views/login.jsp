<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>

<head>
  <title>Login</title>
</head>

<body>
<h2>Welcome to our courses!</h2>
If you are our student, you can sign up for a course on next page. <br/>
If you are our lecturer, you can add a mark and a feedback to
the student. <br/> <br/>
Please, enter your data first to login (or click
<a href="controller?command=studentRegisterPage">here</a> to register,
as a new student) <br/>
<%--Test message:${testMessage} ${client.login}--%>
<b> ${errorMessage} </b>
<br/>
<%--<form name="loginForm" method="POST" action="controller">
  <input type="hidden" name="command" value="login" /> Login:<br />
  <input type="text" name="login" value="" /> <br /> Password:<br />
  <input type="password" name="password" value="" /> <br />
  <input type="submit" value="Log in" />
</form>--%>

<s:form method="post" modelAttribute="client" action="checkLogin">

    <label for="login">Login:</label>
    <s:input path="login" placeholder="Enter login" required="required"/> <br/>
    <label for="password" >Password:</label>
    <s:input path="password" placeholder="Enter password" required="required"/> <br/> <br/>
    <input type="submit" value="Log in"/>

</s:form>

<b>where: </b>
<br/> students login/password: StudentX / XXX (X=0..5)
<br/> lecturer login/password: LecturerX / XXX (X=1..3)
<br/>
<br/>Debug info - session = ${sessionScope}
<br/> (You can see log info also in D:\facultative.log)
<br/> ${wrongAction}
<br/> ${nullPage}
<br/>
</body>
</html>