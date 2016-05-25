<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
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
${client.login}, hello!<br/>
<b> You can give a mark and add a feedback to a student: </b><br/>

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
      <td><b>mark:</b>
        <c:if test="${student.mark != 0}"> ${student.mark} </c:if></td>
      <td colspan="3"><b>feedback:</b> ${student.feedback}</td>
    </tr>
  </c:forEach>
</table>
<br/>

<%--<form name="addMarkFeedbackForm" method="POST" action="controller">
  <input type="hidden" name="command" value="addMarkFeedback"/>
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
</form>--%>

  <s:form method="post" modelAttribute="client" action="addMarkFeedback">
  <b><label for="id">Student ID*</label></b> <br/>
  <s:input path="id" type="text" value="" placeholder="Enter student id" required="required"/> <br/> <br/>
  <b> Mark* </b><br/>
  <p><s:radiobutton path="mark" value="2"/> 2 </p>
  <p><s:radiobutton path="mark" value="3"/> 3 </p>
  <p><s:radiobutton path="mark" value="4"/> 4 </p>
  <p><s:radiobutton path="mark" value="5"/> 5 </p>
  <b><label for="feedback">Feedback*</label></b><br/>
    <s:input path="feedback" type="text" value="" placeholder="Enter feedback" required="required"/> <br/>
  <input type="submit" value="That's my decision!"/>
</s:form>

<button onclick="goBack()">Go Back</button>
<br/>
<script>
  function goBack() {
    window.history.back();
  }
</script>

(* - required)<br/><br/>
Debug info - session = ${sessionScope}
<a href="logout">Logout</a>
</body>

</html>