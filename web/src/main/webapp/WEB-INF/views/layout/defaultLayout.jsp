<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
</head>
<body style="background-color: #FFF">
<div class="page">
  <tiles:insertAttribute name="header"/>
  <div class="content">
    <div id="body">
      <tiles:insertAttribute name="body"/>
    </div>
  </div>
  <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>