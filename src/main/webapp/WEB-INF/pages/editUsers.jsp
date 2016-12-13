<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<c:if test="${sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk2:footerAdmin></vk2:footerAdmin>
</c:if>
<c:if test="${!sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk:footer></vk:footer>
</c:if>

<table border="2" align="center">
    <tr>
        <td>
            Логин
        </td>
        <td>
            Роль
        </td>
        <td>
            Изменить
        </td>
    </tr>
<c:forEach items="${users}" var="user" varStatus="varStatus">
    <tr>
        <form action="/setNewRole" method="post">
            <td><input name = "login" readonly type="text" value="${user.login}"/></td>
            <td>${user.roleName}</td>
            <td><input type="submit" value="Изменить"></td>
        </form>
    </tr>
</c:forEach>
</table>

</body>
</html>
