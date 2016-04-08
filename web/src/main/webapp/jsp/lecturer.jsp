<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
  <title>Welcome lecturer</title>
  <style>
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }

    th, td {
      padding: 5px;
      text-align: left;
    }
  </style>
</head>

<body>
<h3>Welcome, Lecturer!</h3>
<hr/>
${user}, hello!<br/>
<b> You can give a mark and add a feedback to a student: </b><br/>
(You have ${sessionLifetime} seconds to do it) <br/><br/>

<table style="width:90%">
  <tr>
    <th colspan="4">Students list:</th>
  </tr>
  <c:forEach var="student" items="${students}">
    <tr>
      <td>Student ID:${student.id}</td>
      <td>${student.firstName}</td>
      <td>${student.lastName}</td>
      <td>(Login: ${student.login})</td>
    </tr>
    <tr>
      <td><b>Course:</b> ${student.learningCourse}</td>
      <td><b>mark:</b>
        <c:if test="${student.mark != 0}"> ${student.mark} </c:if></td>
      <td colspan="2"><b>feedback:</b> ${student.feedback}</td>
    </tr>
  </c:forEach>
</table>
<br/>

<form name="addMarkFeedbackForm" method="POST" action="controller">
  <input type="hidden" name="command" value="addMarkFeedback"/>
  <b> Course*:</b> <br/>
  <p><input name="courseId" type="radio" value="1"> Mathematics</p>
  <p><input name="courseId" type="radio" value="2"> Physics</p>
  <p><input name="courseId" type="radio" value="3"> English</p>
  <b>Student ID*</b> <br/>
  <input type="text" name="studentId" value=""/> <br/> <br/>
  <b> Mark* </b><br/>
  <p><input name="mark" type="radio" value="2"> 2</p>
  <p><input name="mark" type="radio" value="3"> 3</p>
  <p><input name="mark" type="radio" value="4"> 4</p>
  <p><input name="mark" type="radio" value="5"> 5</p>
  <b>Feedback* </b><br/>
  <input type="text" name="feedback" value=""/> <br/>
  <input type="submit" value="That's my decision!"/>
</form>

<button onclick="goBack()">Go Back</button>
<br/>
<script>
  function goBack() {
    window.history.back();
  }
</script>

(* - required)<br/><br/>
Debug info - session = ${sessionScope}
<a href="controller?command=logout">Logout</a>
</body>

</html>