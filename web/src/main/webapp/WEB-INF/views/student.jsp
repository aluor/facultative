<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <title>Welcome student</title>
</head>

<body>
	<h3><spring:message code="sWelcome"/></h3>
	<hr />${client.login}, <spring:message code="hello"/><br />
	<b><spring:message code="sLine01"/> </b><br />

	 <s:form method="post" modelAttribute="client" action="chooseLearningCourse">
   <p><b><spring:message code="choice"/></b></p>
    <p><s:radiobutton path="choice" value="1" checked="checked"/> <spring:message code="mathematics"/></p>
    <p><s:radiobutton path="choice" value="2"/> <spring:message code="physics"/></p>
    <p><s:radiobutton path="choice" value="3"/> <spring:message code="english"/></p>
    <p><input type="submit" value="OK"></p>
    </s:form>
  
  <button onclick="goBack()"><spring:message code="goBack"/></button> <br />
<script>
function goBack() {
    window.history.back();
}
</script>
	
	<br /> Debug info - session = ${sessionScope}
  <a href="logout"><spring:message code="logout"/></a>
</body>

</html>