<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Корзина</title>
        <jsp:include page="./fragment/head.jsp" />
    </head>
    <body>
        <jsp:include page="fragment/menu.jsp" >
            <jsp:param name="pageName" value="cart" />
        </jsp:include>
        <h1>Корзина</h1>
    </body>
</html>
