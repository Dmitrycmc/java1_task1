<%@ page import="ru.geekbrains.model.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Каталог</title>
        <% HashMap<Integer, Product> products = (HashMap<Integer, Product>) application.getAttribute("products"); %>
        <jsp:include page="./fragment/head.jsp" />
    </head>
    <body>
        <jsp:include page="fragment/menu.jsp" >
            <jsp:param name="pageName" value="catalog" />
        </jsp:include>
        <h1>Каталог</h1>
        <ol>
            <%
                for (Map.Entry<Integer, Product> pair : products.entrySet()) {
            %>
                <li>
                    <a href="./product?id=<%= pair.getKey() %>">
                        <%= pair.getValue().getName() %> (<%= pair.getValue().getPrice() %>)
                    </a>
                </li>
            <%
                }
            %>
        </ol>
    </body>
</html>
