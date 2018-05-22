<?xml version='1.0' encoding='UTF-8' ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mark-tag" uri="/WEB-INF/showMark.tld" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<head>
    <title><fmt:message key="main.label.homepage"/></title>
</head>
<body>
<h2><fmt:message key="main.label.student_courses"/></h2>
<hr/>
<fmt:message key="student_main.message.student" var="studentValue"/>
<fmt:message key="main.message.hello" var="helloValue"/>

<c:out value="${studentValue} ${user.name}, ${helloValue}!"/>
<hr/>
<p><fmt:message key="student_main.message.available_courses"/>:</p>
<hr/>
<c:forEach items="${availableCourses}" var="course">
    <form name="joinCourseForm" method="POST" action="/">
        <input type="hidden" name="command" value="joinCourse"/>
        <input type="hidden" name="courseId" value="${course.courseId}"/>
        <input type="hidden" name="studentId" value="${user.userId}">
        <fmt:message key="main.message.course_name" var="courseNameValue"/>
        <c:out value="${courseNameValue}: ${course.nameCourse}"/>
        <fmt:message key="student_main.button.join_course" var="joinCourseValue"/>
        <input type="submit" value="${joinCourseValue}">
    </form>
</c:forEach>
<hr/>
<p><fmt:message key="student_main.message.my_courses"/>:</p>
<hr/>
<c:forEach items="${studentCourses}" var="course">
    <form name="showMarkForm" method="POST" action="/">
        <input type="hidden" name="command" value="removeUser">
        <input type="hidden" name="courseId" value="${course.courseId}">
        <input type="hidden" name="studentId" value="${user.userId}">
        <input type="hidden" name="courseName" value="${course.nameCourse}">
        <c:out value="${courseNameValue}: ${course.nameCourse}"/>
        <fmt:message key="main.message.mark" var="markValue"/>
        <c:out value="${markValue}:"/>
        <mark-tag:showMark userId="${user.userId}" courseId="${course.courseId}"/>
        <fmt:message key="student_main.button.remove_course" var="removeCourseValue"/>
        <input type="submit" value="${removeCourseValue}">
    </form>

</c:forEach>
<div style="position: fixed; top: 2%; right: 2%;">
    <form name="logoutForm" method="POST" action="/">
        <input type="hidden" name="command" value="logout"/>
        <fmt:message key="main.button.logout" var="logoutValue"/>
        <input type="submit" value="${logoutValue}">
    </form>
</div>

<div style="position: fixed; top: 2%; right: 7%;">
    <form name="languageForm" method="POST" action="/">
        <input type="hidden" name="command" value="changeLanguage"/>
        <input type="hidden" name="languageKey" value="">
        <input type="submit" value="EN">
    </form>
</div>

<div style="position: fixed; top: 2%; right: 10%;">
    <form name="languageForm" method="POST" action="/">
        <input type="hidden" name="command" value="changeLanguage"/>
        <input type="hidden" name="languageKey" value="ua">
        <input type="submit" value="UA">
    </form>
</div>
</body>
</html>