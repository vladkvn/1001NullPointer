<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
<%--
  Created by IntelliJ IDEA.
  User: vladkvn
  Date: 09.12.2016
  Time: 22:21
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
<br>
<div align="center" style="width: 75%">
    <p style="margin-right: 45%">
        Список контрактов: <br>
                    ${company.companyName}
    </p>
</div>
<br>
<c:if test="${sessionScope.get(\"roleName\").equals(\"admin\")}">
    <c:if test="${contracts.size()==0}">
        <p align="center">
        Cписок контрактов пуст
        </p>
    </c:if>
</c:if>
<c:if test="${contracts.size()!=0}">
<table border="1" align="center" width="75%">
<c:forEach items="${contracts}" var="contract" varStatus="varStatus">
    <tr>
        <td width="10%">
            ${varStatus.index}
        </td>
        <td>
            <a href="/Contract${contract.id}">${contract.discription}</a>
        </td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>
