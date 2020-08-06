package ru.geekbrains.template;

public class Menu {
    public static String render() {
        return "<ul style='" +
                "    list-style: none;\n" +
                "    display: flex;\n" +
                "    justify-content: space-evenly;\n" +
                "    position: sticky;\n" +
                "    top: 0;\n" +
                "    height: 100px;\n" +
                "    background: #a3d9ff;\n" +
                "    align-items: center;" +
                "'>" +
                "   <li><a href='./main'>Главная</a></li>" +
                "   <li><a href='./catalog'>Каталог</a></li>" +
                "   <li><a href='./product'>Продукт</a></li>" +
                "   <li><a href='./cart'>Корзина</a></li>" +
                "   <li><a href='./order'>Оформление заказа</a></li>" +
                "</ul>";
    }
}
