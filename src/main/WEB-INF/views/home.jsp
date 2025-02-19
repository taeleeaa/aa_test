<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To E7E World</title>
</head>
<body>
<sec:authorize access="!isAuthenticated()">
    <a href ="/login">로그인 페이지롱</a>
    <h1>E7E 세상에 오신걸 좋아해용</h1>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <h1><sec:authentication property="name"/>님!!</h1>
    <h1>E7E 세상에 오신걸 좋아해용</h1>
    <a href="/auth">인증받은자여 요기 오랑</a>
    <form action="/logout">
        <sec:csrfInput />
        <button>로그아웃할꼉</button>
    </form>
</sec:authorize>
</body>
</html>