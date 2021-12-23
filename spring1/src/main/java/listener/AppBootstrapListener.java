package listener;

import model.Product;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;

@WebListener
public class AppBootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        HashMap<Integer, Product> products = new HashMap<>();
        Product product1 = new Product("Хлеб", 30);
        Product product2 = new Product("Молоко", 60);
        Product product3 = new Product("Сыр", 150);
        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);

        sce.getServletContext().setAttribute("products", products);
    }
}
