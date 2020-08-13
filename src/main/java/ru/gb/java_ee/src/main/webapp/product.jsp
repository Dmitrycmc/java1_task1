<%@ page import="ru.geekbrains.model.Product" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Продукт</title>
        <% HashMap<Integer, Product> products = (HashMap<Integer, Product>) application.getAttribute("products"); %>
        <% Integer id = Integer.parseInt(request.getParameter("id")); %>
        <% Product product = products.get(id); %>
    </head>
    <body>
        <jsp:include page="menu.jsp" />
        <h1>Продукт</h1>
        <p><%= product.getName() %></p>
        <p><%= product.getPrice() %></p>
    </body>
</html>
