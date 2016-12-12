<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div>
    <form action="/cr_Con" method="post">
        <input type="text" name="discription" placeholder="Краткое описание">
        <input type="submit" value="Создать">
    </form>
</div>
</body>
</html>
