<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Главная</title>
        <jsp:include page="./fragment/head.jsp" />
    </head>
    <body>
        <jsp:include page="fragment/menu.jsp" >
            <jsp:param name="pageName" value="main" />
        </jsp:include>
        <h1>Главная</h1>
    </body>
</html>
