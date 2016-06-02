<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>

<head>
  <title>Login</title>
</head>

<body>
<a href="?locale=en">English </a> | <a href="?locale=ru">Русский </a>
<h2><spring:message code="page.header"/></h2>
<spring:message code="line01"/> <br/>
<spring:message code="line02"/>
<spring:message code="line03"/> <br/> <br/>
<spring:message code="line04"/>
<a href="studentRegisterPage"><spring:message code="here"/></a> <spring:message code="line05"/> <br/>

<b> ${errorMessage} </b>
<br/>


<s:form method="post" modelAttribute="client" action="checkLogin">

    <label for="login"><spring:message code="login"/></label>
    <s:input path="login" placeholder="Enter login" required="required"/> <br/>
    <label for="password" ><spring:message code="password"/></label>
    <s:input path="password" placeholder="Enter password" required="required"/> <br/> <br/>
    <input type="submit" value="Log in"/>

</s:form>

<b><spring:message code="where"/> </b>
<br/> <spring:message code="line06"/>
<br/> <spring:message code="line07"/>
<br/>
<br/>Debug info - session = ${sessionScope}
<br/> (You can see log info also in D:\facultative.log)
<br/> ${wrongAction}
<br/> ${nullPage}
<br/>
</body>
</html>