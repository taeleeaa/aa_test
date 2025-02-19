<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To E7E World</title>
</head>
<body>

<sec:authorize access="!hasRole('ALBA')">
    <h1>인간적으로 알바도 좀 쉽시당!~~ㅠㅠ</h1>
</sec:authorize>

<sec:authorize access="hasRole('ALBA')">
    <h1><sec:authentication property="name" /> 알바님 어서오삼</h1>
    <h2>수고가 많으삼 조선판 노비님!</h2>
</sec:authorize>

<a href="javascript:history.back()">뒤로가깅</a>
<form action="/logout">
    <sec:csrfInput />
    <button>로그아웃</button>
</form>

</body>
</html>