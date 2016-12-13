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
        Ваш список контрактов пуст
    </c:if>
</c:if>
<table border="1" align="center" width="75%">
    <c:forEach items="${companies}" var="company" varStatus="varStatus">
        <tr>
            <td width="10%">
                    ${varStatus.index}
            </td>
            <td>
                <a href="/viewCompany${company.companyId}">${company.companyName}</a>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
