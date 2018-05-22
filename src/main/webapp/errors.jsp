<?xml version='1.0' encoding='UTF-8' ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <title><fmt:message key="error.message.error"/></title>
</head>
<body><h3><fmt:message key="error.message.error"/></h3>
<hr/>

<fmt:message key="${error}"/>
<hr/>


<form name="backForm" method="POST" action="/">
    <input type="hidden" name="command" value="back">
    <fmt:message key="main.button.back" var="backValue"/>
    <input type="submit" value="${backValue}">
</form>

</body>
</html>
