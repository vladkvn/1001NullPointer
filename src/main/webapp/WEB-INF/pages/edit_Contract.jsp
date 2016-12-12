<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
<%--
  Created by IntelliJ IDEA.
  User: vladkvn
  Date: 09.12.2016
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
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

<table align="center" width="75%">
        <form action="/updateContract${id}" method="post">
            <tr>
                <td>
            <input style="width: 100%; height: 50px;" value="${contract.discription}" name="discription" type="text"/>
                </td>
                <td rowspan="2" width="25%">
                    <input style="width:100%; height: 40%;" type="submit" value="Сохранить изменения"/>
                </td>
            </tr>
            <tr>
                <td>
                    <textarea style="width: 100%; height: 500px"  height="500px" name="fullDiscription" type="te">${contract.fullDiscription}</textarea>
                </td>
            </tr>
        </form>
        <tr>
            <form action="/addUser${id}" method="post">
                <td>
                <select style="width: 100%" name="login" required="required">
                    <c:forEach items="${usersNot}" var="user" varStatus="varStatus">
                        <option>${user.login}</option>
                    </c:forEach>
                </select>
                </td>
                <td>
                    <input style="width:100%; height: 100%;" type="submit" value="Добавить"/>
                </td>
            </form>
        </tr>
    <tr>
        <form action="/deleteUserFromContract${id}" method="post">
            <td>
                <select style="width: 100%" name="login" required="required">
                    <c:forEach items="${contract.usersList}" var="user" varStatus="varStatus">
                        <option>${user.login}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input style="width:100%; height: 100%;" type="submit" value="Удалить"/>
            </td>
        </form>
    </tr>
</table>
</body>
</html>
