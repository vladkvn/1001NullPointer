<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: vladkvn
  Date: 12.12.2016
  Time: 3:17
  To change this template use File | Settings | File Templates.
--%>
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
<form action="/updateInfo" method="post">

    <table border="1" align="center" style="width: 60%">
        <tr>
            <td width="25%">
                Фамилия
            </td>
            <td>
                <input style="width: 100%" type="text" name="LN" value="${info.LN}">
            </td>
        </tr>
        <tr>
            <td width="25%">
                Имя
            </td>
            <td>
                <input style="width: 100%" type="text" name="FN" value="${info.FN}">
            </td>
        </tr>
        <tr>
            <td width="25%">
                Дата рождения
            </td>
            <td>
                <input style="width: 100%" type="text" name="age" value="${info.age}">
            </td>
        </tr>
        <tr>
            <td width="25%">
                Образование
            </td>
            <td>
                <input style="width: 100%" type="text" name="education" value="${info.education}">
            </td>
        </tr>
        <tr>
            <td width="25%">
                Город
            </td>
            <td>
                <input style="width: 100%" type="text" name="city" value="${info.city}">
            </td>
        </tr>
        <tr>
            <td width="25%">
                Пол
            </td>
            <td>
                <input style="width: 100%" type="text" name="sex" value="${info.sex}">
            </td>
        </tr>
    </table>
    <div align="center">
        <input style="width: 50%" type="submit" value="Обновить информацию">
    </div>
</form>
</body>
</html>
