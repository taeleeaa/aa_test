<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome To E7E World</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
<h1>E7E World Login</h1>
<!--  post에 /login은  Spring Security가 자동으로 처리 해줘용!!
      get /login 직접 화면 만드는 URL가 초보자들은 엄칭 헷갈려해용
 -->
<form action="/login" method='POST'>
    <table>
        <tr>
            <td>멤버:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>암호:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td>날 기억해줄랭:</td>
            <td><input type="checkbox" name="remember-me" /></td>
        </tr>
        <tr>
            <sec:csrfInput/> <!-- csrf 토큰 넣깅 -->
            <td><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>
</form>
<script>
    // AJAX 사용시 csrf 토큰 생성은 꼬옥 이렇게 할 필요는 없음
    const csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");
    const csrfToken = $("meta[name='_csrf']").attr("content");

    //서버에서 발행된 헤더네임과 토큰갑사 저장, 요거이 좀더 편함!
    const header = '${_csrf.headerName}';
    const token = '${_csrf.token}';
    console.log("체킁:",header, token);  // 누느로 화긴
</script>
</body>
</html>