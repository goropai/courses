<?xml version='1.0' encoding='UTF-8' ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="my-tag" uri="WEB-INF/showAuthorTag.tld" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<head>
    <title><fmt:message key="login.label.username"/></title>
</head>
<body>
<h3><fmt:message key="login.label.username"/></h3>
<hr/>
<form name="loginForm" method="POST" action="/">
    <input type="hidden" name="command" value="login"/>
    <label for="username"><fmt:message key="login.label.username"/>:</label>
    <input type="text" id="username" name="login" value=""> <br/>
    <br/>
    <label for="password"><fmt:message key="login.label.password"/>:</label>
    <input type="password" id="password" name="password" value=""> <br/>
    <br/>
    <fmt:message key="login.button.submit" var="submitValue"/>
    <input type="submit" value="${submitValue}">
    <fmt:message key="login.button.register" var="registerValue"/>
    <input type="button" value="${registerValue}" onClick='location.href="/register.jsp"'/>
</form>
<c:if test="${not empty error}">
    <fmt:message key="${error}"/>
</c:if>

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
<div style="position: fixed; top: 95%; right: 50%;">
    <my-tag:showAuthor/>
</div>

<hr/>
</body>
</html>