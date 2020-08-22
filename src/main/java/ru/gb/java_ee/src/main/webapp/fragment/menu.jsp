<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="menu" >
   <li><a class="${requestScope.activePage == "main" ? "active" : ""}" href='./main'>Главная</a></li>
   <li><a class="${requestScope.activePage == "catalog" ? "active" : ""}" href='./catalog'>Каталог</a></li>
   <li><a class="${requestScope.activePage == "cart" ? "active" : ""}" href='./cart'>Корзина</a></li>
   <li><a class="${requestScope.activePage == "order" ? "active" : ""}" href='./order'>Оформление заказа</a></li>
</ul>
