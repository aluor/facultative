<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <title>Operation successfull</title>
</head>

<body>
	<h3>Operation successfull!</h3>
	
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