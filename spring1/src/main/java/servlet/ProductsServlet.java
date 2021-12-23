package servlet;

import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/products/*")
public class ProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<Integer, Product> products = (HashMap<Integer, Product>) req.getServletContext().getAttribute("products");

        if (req.getPathInfo() == null) {
            resp.getWriter().write("<table>");
            resp.getWriter().write("<tr>");
            resp.getWriter().write("<th>Id</th>");
            resp.getWriter().write("<th>Name</th>");
            resp.getWriter().write("<th>Price</th>");
            resp.getWriter().write("</tr>");

            for (Product product : products.values()) {
                resp.getWriter().write("<tr>");
                resp.getWriter().write("<td>" + product.getId() + "</td>");
                resp.getWriter().write("<td><a href='products/" + product.getId() + "'>" + product.getName() + "</a></td>");
                resp.getWriter().write("<td>" + product.getPrice() + " ₽</td>");
                resp.getWriter().write("</tr>");
            }
            resp.getWriter().write("</table>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(req.getPathInfo().substring(1));
        } catch (Exception e) {
            resp.getWriter().println("400: Bad url");
            return;
        }

        if (products.get(id) != null) {
            resp.getWriter().println(products.get(id).getId());
            resp.getWriter().println(products.get(id).getName());
            resp.getWriter().println(products.get(id).getPrice() + " ₽");
            return;
        }
        resp.getWriter().println("404: Товар не найден!");
    }
}
