<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<link rel="stylesheet" href="css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="css/style.css" type="text/css">
<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/jquery-2.2.2.js" type="text/javascript"></script>
  
  <title>Operation failed</title>
</head>

<body>
<div class="alert alert-error">
   <img alt="backgroundPicture.jpg" src="img/glyphicons-halflings.png">
	<h3>Operation failed!</h3>
	<b> ${errorMessage} </b> <br /> 	<br />
	
	<button class="btn btn-info" onclick="goBack()">Go Back</button> <br />
<script>
function goBack() {
    window.history.back();
}
</script> <br /> 
		
	Debug info - session = ${sessionScope}
	<a href="logout"> <b>Logout</b></a>
	</div>
</body>

</html>