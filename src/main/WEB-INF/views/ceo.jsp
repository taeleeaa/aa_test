<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To E7E World</title>
</head>
<body>
<p><sec:authentication property="principal" /></p>

<sec:authorize access="hasRole('CEO') and hasRole('MANAGER')">
    <h4>사장님 강령하소성 ~~ 아붕</h4>
</sec:authorize>

<h1><sec:authentication property="name" />사장님</h1>
<a href="javascript:history.back()">뒤로가깅</a>
<form action="/logout">
    <sec:csrfInput />
    <button>로그아웃</button>
</form>
</body>
</html>