<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk2:footerAdmin></vk2:footerAdmin>
</c:if>
<c:if test="${!sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk:footer></vk:footer>
</c:if>
<table border="1" align="center" style="width: 60%">
    <tr>
        <td width="25%">
            Фамилия
        </td>
        <td>
            ${info.LN}
        </td>
    </tr>
    <tr>
        <td width="25%">
            Имя
        </td>
        <td>
            ${info.FN}
        </td>
    </tr>
    <tr>
        <td width="25%">
            Дата рождения
        </td>
        <td>
            ${info.age}
        </td>
    </tr>
    <tr>
        <td width="25%">
            Образование
        </td>
        <td>
            ${info.education}
        </td>
    </tr>
    <tr>
        <td width="25%">
            Город
        </td>
        <td>
            ${info.city}
        </td>
    </tr>
    <tr>
        <td width="25%">
            Пол
        </td>
        <td>
            ${info.sex}
        </td>
    </tr>
</table>

<div align="center">
    <form action="/editInfo" method="post" >
        <input type="submit" value="Изменить профиль">
    </form>
</div>
</body>
</html>
