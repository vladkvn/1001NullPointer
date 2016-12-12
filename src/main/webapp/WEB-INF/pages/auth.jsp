<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib uri="/WEB-INF/footer.tld" prefix="vk"%>--%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Авторизация</title>
</head>
<body>
<%--<vk:footer></vk:footer>--%>
${message}
<d align="center">
<form action="/auth" method="post" style="margin-top: 300px">
    <input type="text" name="login"/>
    <input type="password" name="pass"/>
    <input type="submit"/>
</form>
<a href="/reg">Регистрация</a>
</d>
</body>
</html>
