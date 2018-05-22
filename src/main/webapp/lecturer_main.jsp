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
<body><h2><fmt:message key="main.label.student_courses"/></h2>
<hr/>
<fmt:message key="lecturer_main.message.lecturer" var="lecturerValue"/>
<fmt:message key="main.message.hello" var="helloValue"/>

<c:out value="${lecturerValue} ${user.name}, ${helloValue}!"/>
<c:if test="${empty courses}">
    <form name="insertCourseForm" method="POST" action="/">
        <hr/>
        <input type="hidden" name="command" value="insertCourse">
        <input type="hidden" name="userId" value="${user.userId}">
        <label for="newNameCourse"><fmt:message key="lecturer_main.label.new_course_name"/>:</label>
        <input type="text" id="newNameCourse" name="newNameCourse" value="">
        <fmt:message key="lecturer_main.button.insert_course" var="insertCourseValue"/>
        <input type="submit" value="${insertCourseValue}">
    </form>
</c:if>
<hr/>
<c:forEach items="${courses}" var="course">
    <form name="updateCourseForm" method="POST" action="/">
        <input type="hidden" name="command" value="updateCourse"/>
        <input type="hidden" name="courseId" value="${course.courseId}"/>
        <fmt:message key="main.message.course_name" var="courseNameValue"/>
        <c:out value="${courseNameValue}: ${course.nameCourse}"/> </br>
        </br>
        <fmt:message key="lecturer_main.message.change_course_name"/>
        <input type="text" name="nameCourse" value="">
        <fmt:message key="lecturer_main.button.change_name" var="changeNameValue"/>
        <input type="submit" value="${changeNameValue}">
    </form>

    <form name="deleteCourseForm" method="POST" action="/">
        <input type="hidden" name="command" value="deleteCourse">
        <input type="hidden" name="courseId" value="${course.courseId}">
        <fmt:message key="lecturer_main.button.delete_course" var="deleteCourseVar"/>
        <input type="submit" name="command" value="${deleteCourseVar}">
    </form>

    <hr/>
    <p><fmt:message key="lecturer_main.message.students"/>: </p>
    <hr/>

    <c:forEach items="${students}" var="student">
        <form name="showMarkForm" method="POST" action="/">
            <input type="hidden" name="command" value="showMark">
            <input type="hidden" name="courseId" value="${course.courseId}">
            <input type="hidden" name="studentId" value="${student.userId}">
            <input type="hidden" name="studentName" value="${student.name}">
            <input type="hidden" name="studentSurname" value="${student.surname}">
            <fmt:message key="login.label.username" var="loginValue"/>
            <fmt:message key="lecturer_main.message.full_name" var="fullNameValue"/>
            <c:out value="${loginValue}: [${student.login}]
                                  ${fullNameValue}: [${student.name} ${student.surname}]"/>
            <fmt:message key="main.message.mark" var="markValue"/>
            <c:out value="${markValue}:"/>
            <mark-tag:showMark userId="${student.userId}" courseId="${course.courseId}"/>
        </form>
        <form name="inputMarkForm" method="POST" action="/">
            <input type="hidden" name="command" value="inputMark">
            <input type="hidden" name="courseId" value="${course.courseId}">
            <input type="hidden" name="studentId" value="${student.userId}">
            <input type="text" placeholder="1-5" size="1" name="mark" value="">
            <fmt:message key="lecturer_main.button.input_mark" var="inputMarkValue"/>
            <input type="submit" value="${inputMarkValue}">
        </form>
        <form name="removeUserForm" method="POST" action="/">
            <input type="hidden" name="command" value="removeUser">
            <input type="hidden" name="courseId" value="${course.courseId}">
            <input type="hidden" name="studentId" value="${student.userId}">
            <fmt:message key="lecturer_main.button.remove_student" var="removeStudentValue"/>
            <input type="submit" value="${removeStudentValue}">
        </form>
        <hr/>

    </c:forEach>
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
