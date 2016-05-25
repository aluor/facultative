<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<html>

<head>
  <title>Welcome student</title>
</head>

<body>
	<h3>Welcome, Student!</h3>
	<hr />${client.login}, hello!<br />
	<b>Choose a course, you'd like to learn </b><br />

 <%-- <form name="chooseCourseForm" method="POST" action="controller">
    <input type="hidden" name="command" value="chooseLearningCourse" />
    <p><b>Your choice:</b></p>
    <p><input name="choice" type="radio" value="1" checked="checked"> Mathematics</p>
    <p><input name="choice" type="radio" value="2"> Physics</p>
    <p><input name="choice" type="radio" value="3"> English</p>
    <p><input type="submit" value="I understand, what I'm doing"></p>
  </form>--%>

	 <s:form method="post" modelAttribute="client" action="chooseLearningCourse">
   <p><b>Your choice:</b></p>
    <p><s:radiobutton path="choice" value="1" checked="checked"/> Mathematics</p>
    <p><s:radiobutton path="choice" value="2"/> Physics</p>
    <p><s:radiobutton path="choice" value="3"/> English</p>
    <p><input type="submit" value="I understand, what I'm doing"></p>
    </s:form>
  
  <button onclick="goBack()">Go Back</button> <br />
<script>
function goBack() {
    window.history.back();
}
</script>
	
	<br /> Debug info - session = ${sessionScope}
  <a href="logout">Logout</a>
</body>

</html>