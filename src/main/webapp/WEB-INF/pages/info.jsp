<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<vk:footer/>
<p>
    ${info.LN}<br>
    ${info.sex}<br>
    ${info.age}<br>
    ${info.education}<br>
    ${info.city}<br>
    ${info.FN}<br>
</p>

<form action="/cr_Con" method="get">
    <input type="submit" value="Новый контракс">
</form>

<form action=/exit method="post">
    <input type="submit" value="Выход">
</form>

</body>
</html>
