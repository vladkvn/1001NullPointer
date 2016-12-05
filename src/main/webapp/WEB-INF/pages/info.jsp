<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/updateInfo">
    <table>
        <tr>
            <tb><input type="text" name="LN" value="${info.LN}" placeholder="Фамилия"/></tb>
            <tb>${errorLN}</tb>
        </tr>

        <tr>
            <tb><input type="text" name="FN" value="${info.FN}" placeholder="Имя"/></tb>
            <tb>${errorFN}</tb>
        </tr>

        <tr>
            <tb><input type="text" name="city" value="${info.city}" placeholder="Город"/></tb>
            <tb>${errorCity}</tb>
        </tr>
        <tr>
            <tb><input type="text" name="education" value="${info.education}" placeholder="Образование"/></tb>
            <tb>${errorEducation}</tb>
        </tr>
        <tr>
            <tb><input type="text" name="age" value="${info.age}" placeholder="Дата Рождения"/></tb>
            <tb>${errorAge}</tb>
        </tr>
        <tr>
            <tb><input type="text" name="sex" value="${info.sex}" placeholder="Пол"/></tb>
            <tb>${errorSex}</tb>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit">
            </td>
        </tr>
    </table>
</form>

<form action=/exit method="post">
    <input type="submit" value="Выход">
</form>

</body>
</html>
