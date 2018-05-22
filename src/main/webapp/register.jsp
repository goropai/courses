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
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<head>
    <title><fmt:message key="register.message.registration"/></title>
</head>
<body>
<h3><fmt:message key="register.message.registration"/></h3>
<hr/>
<form name="registerForm" method="POST" action="/">
    <input type="hidden" name="command" value="register"/>
    <label for="username"><fmt:message key="login.label.username"/>:</label>
    <input type="text" id="username" name="login" value=""> <br/>
    <br/>
    <label for="password"><fmt:message key="login.label.password"/>:</label>
    <input type="password" id="password" name="password" value=""> <br/>
    <br/>
    <label for="name"><fmt:message key="register.label.name"/>:</label>
    <input type="text" id="name" name="name" value=""> <br/>
    <br/>
    <label for="surname"><fmt:message key="register.label.surname"/>:</label>
    <input type="text" id="surname" name="surname" value=""> <br/>
    <br/>
    <fmt:message key="register.button.submit" var="submitValue"/>
    <input type="submit" value="${submitValue}">
    <fmt:message key="main.button.back" var="backValue"/>
    <input type="button" value="${backValue}" onClick='location.href="/"'>
</form>
<hr/>
<div style="position: fixed; top: 2%; right: 2%;">
    <form name="languageForm" method="POST" action="/">
        <input type="hidden" name="command" value="changeLanguage"/>
        <input type="hidden" name="languageKey" value="">
        <input type="submit" value="EN">
    </form>
</div>

<div style="position: fixed; top: 2%; right: 5%;">
    <form name="languageForm" method="POST" action="/">
        <input type="hidden" name="command" value="changeLanguage"/>
        <input type="hidden" name="languageKey" value="ua">
        <input type="submit" value="UA">
    </form>
</div>
</body>
</html>
