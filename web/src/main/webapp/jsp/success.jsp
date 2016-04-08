<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<a href="controller?command=logout">Logout</a>
</body>

</html>