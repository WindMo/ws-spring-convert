<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>welcome</title>
    <meta charset="UTF-8">
</head>
<body>
<form:form modelAttribute="wrapper">
    <%--  1-2-3  --%>
    <h6><from:input path="code"/></h6>
    <%--  4=5=6  --%>
    <h6><from:input path="number"/></h6>
</form:form>
<H1>JSP</H1>
</body>
</html>