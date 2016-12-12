<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<d align="center">
    ${message}
    <form action="/reg" method="post"style="margin-top: 300px">
        <input type="text" name="login" placeholder="логин" value="${login}"/>
        <input type="password" name="pass" placeholder="пароль"/>
        <input type="password" name="pass2" placeholder="повторите пароль"/>
        <input type="submit" value="Регистрация">
    </form>
    <a href="/auth">Авторизация</a>
</d>
</body>
</html>
