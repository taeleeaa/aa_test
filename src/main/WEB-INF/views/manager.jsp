<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To E7E World</title>
</head>
<body>
<sec:authorize access="hasRole('CEO')">
    <h4>사장님은 여기 오는거 아니얌~~ 반칙!!</h4>
</sec:authorize>

<!--
    .requestMatchers("/manager/**").hasAnyRole("CEO","MANAGER")
    FilterChain에 이미 위처럼 등록해  놓았는데, 아래 처럼 사용하는게 으미?
    전역(대표) 설정과 페이지별 내부 설정의 의미를 한번 생각해 보장(중요)
 -->
<sec:authorize access="hasRole('MANAGER')">
    <h4>관리자님 어여 오셔용</h4>
</sec:authorize>

<h1><sec:authentication property="name" /> 관리자님</h1>
<a href="javascript:history.back()">뒤로가깅</a>
<form action="/logout">
    <sec:csrfInput />
    <button>로그아웃</button>
</form>
</body>
</html>