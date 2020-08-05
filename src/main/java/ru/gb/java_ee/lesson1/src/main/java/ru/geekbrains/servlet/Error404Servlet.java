package ru.geekbrains.servlet;

import ru.geekbrains.template.Menu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Error404Servlet", urlPatterns = "/404")
public class Error404Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(render());
    }

    private String render() {
        return Menu.render() + "<h1>Такой страницы не существует.</h1>";
    }
}
