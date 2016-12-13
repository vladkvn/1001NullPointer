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
            var t1 = [
                <c:forEach items="${data}" var="elem" varStatus="varStatus">
                [${varStatus.index},${elem}],
                </c:forEach>
            ];
            var d0 = [[0,0],[0,0]];
            <c:forEach items="${data2}" var="elem" varStatus="varStatus">
            var d${varStatus.count} = [
                <c:choose>
                    <c:when test="${elem==0}">
                        [${varStatus.count}, 0] , [${varStatus.count},0.125]
                    </c:when>
                    <c:otherwise>
                        [${varStatus.count}, 0] , [${varStatus.count},${elem}]
                    </c:otherwise>
            </c:choose>
                    ];
            <c:if test="${varStatus.last}">
                d${varStatus.count+1} = [[${varStatus.count+1}, 0] , [${varStatus.count+1},0]];
            </c:if>
            </c:forEach>

            $.plot($("#placeholder2"),[
            <c:forEach items="${data2}" var="elem" varStatus="varStatus">
                <c:if test="${!varStatus.last}">
                { data: d${varStatus.count}, bars:{show:true}},
                </c:if>
                <c:if test="${varStatus.last}">
                { data: d${varStatus.count},bars:{show:true}}
                </c:if>
            </c:forEach>
        ]);
            $.plot($("#placeholder") ,[t1]);
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
                <tr>
                    <td>
                        <form action="/allCompanies" method="post">
                            <input type="submit" value="Все компании">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="/cr_Com" method="get">
                            <input type="submit" value="Создать компанию">
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
    <tr>
        <td width="75%">
            <div id="content2">
                <div>
                    <div id="placeholder2" style="width:800px;height:400px;"></div>
                </div>
            </div>
        </td>
        <td>
            Легенда:<br>
            <c:forEach items="${legend}" var="elem" varStatus="varStatus">
                ${varStatus.count}| ${elem}<br>
            </c:forEach>
        </td>
    </tr>
</table>

</div>
</body>
</html>
