<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
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
<table align="center" width="75%" border="1">
    <tr>
        <td>
            Краткое описание
        </td>
        <td>
            ${contract.discription}
        </td>
    </tr>
    <tr>
        <td>
            Полное описание
        </td>
        <td>
            ${contract.fullDiscription}
        </td>
    </tr>
    <tr>
        <td>
            Команда
        </td>
        <td>
            <select>
                <c:forEach items="${contract.usersList}" var="user" varStatus="varStatus">
                    <option>${user.login}</option>
                </c:forEach>
            </select>
        </td>
    </tr>
            <c:if test="${sessionScope.get(\"roleName\").equals(\"admin\")}">
                <tr>
                    <td>
                        <form action="/deleteContract${contract.id}" method="post">
                            <input style="width: 100%; height: 100%;" type="submit" value="Удалить контракт">
                        </form>
                    </td>
                    <td>
                        <form action="/edit_Contract${contract.id}" method="post">
                            <input style="width: 100%; height: 100%;" type="submit" value="Изменить контракт">
                        </form>
                    </td>
                </tr>
            </c:if>
</table>
<table align="center" width="60%">
    <c:forEach items="${comments}" var="comment" varStatus="varStatus">
        <tr>
            <td colspan="2">
        <table border="1" width="100%">
                <tr>
                    <td  colspan="2">
                        <a href="/info${comment.user.id}">${comment.user.login}   ${comment.user.info.FN}     ${comment.user.info.LN}</a>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        ${comment.text}
                    </td>
                </tr>
                <c:if test="${(comment.user.login == login)||(sessionScope.get(\"roleName\").equals(\"admin\"))}">
                <tr>
                    <td colspan="2">
                            <form action="/deleteComment${comment.id}" method="post">
                                <input name="contractId" value="${id}" hidden="hidden">
                                <input type="submit" value="Удалить комментарий">
                            </form>
                    </td>
                </tr>
                </c:if>
            </table>
            </td>
            </tr>
        </c:forEach>
    <tr>
        <td>
            <br>
            Добавить комментарий:
            <br>
        </td>
    </tr>
        <tr>
            <td colspan="2">
                <form action="/addComment${id}" method="post">
                    <textarea style="width: 100%;height: 100%" name="text" placeholder="Введите сообщение"></textarea>
                    <input type="submit">
                </form>
            </td>
        </tr>
    </table>
</body>
</html>
