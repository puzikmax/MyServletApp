<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWindow</title>

</head>
<body>


<h2>Все пользователи</h2><br />

<c:forEach var="user" items="${requestScope.users}">
    <ul>

        <li>Имя: <c:out value="${user.name}"/></li>

        <form method="post" action="<c:url value='/delete'/>">
            <input type="number" hidden name="id" value="${user.id}" />
            <input type="submit" name="delete" value="Удалить"/>
        </form>

    </ul>
    <hr />

</c:forEach>

<h2>Создание нового пользователя</h2><br />

<form method="post" action="<c:url value='/add_user'/>">

    <label><input type="text" name="name"></label>Имя<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>
