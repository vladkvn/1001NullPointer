<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="vk" uri="/WEB-INF/footer" %>
<%@ taglib prefix="vk2" uri="/WEB-INF/footerAdmin" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="../../excanvas.min.js"></script><![endif]-->
    <script language="javascript" type="text/javascript" src="<c:url value="flot/jquery.js"/>"></script>
    <script language="javascript" type="text/javascript" src="<c:url value="flot/jquery.flot.js"/>"></script>
    <script type="text/javascript">
        $(function () {
            var d4 = [
                <c:forEach items="${data}" var="elem" varStatus="varStatus">
                [${varStatus.index},${elem}],

                </c:forEach>
            ];
            var d1 = [];
            for (var i = 0; i < 14; i += 0.5)
                d1.push([i, Math.sin(i)]);

            var d2 = [[0, 3], [4, 8], [8, 5], [9, 13]];

            // a null signifies separate line segments
            var d3 = [[0, 12], [7, 12], null, [7, 2.5], [12, 2.5]];

            $.plot($("#placeholder"), [d4]);
        });
    </script>
</head>
<body>
<c:if test="${sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk2:footerAdmin></vk2:footerAdmin>
</c:if>
<c:if test="${!sessionScope.get(\"roleName\").equals(\"admin\")}">
    <vk:footer></vk:footer>
</c:if>
<div align="center" style="width: 75%">
<table style="margin-left: 15%">
    <tr>
        <td width="75%">
            <div id="content">

                <div class="demo-container">
                    <div id="placeholder" style="width:800px;height:400px;"></div>
                </div>

            </div>
        </td>
        <td rowspan="2">
            <table>
                <tr>
                    <td>
                        <form action="/cr_Con" method="get">
                            <input type="submit" value="Новый контракт">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="/allContracts" method="post">
                            <input type="submit" value="Все контракты">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="/userManage" method="post">
                            <input type="submit" value="Менеджер пользователей">
                        </form>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="margin-left: 50px">
            График Зависимости кол-ва комментариев от кол-ва контрактов
        </td>
    </tr>
</table>

</div>
</body>
</html>
