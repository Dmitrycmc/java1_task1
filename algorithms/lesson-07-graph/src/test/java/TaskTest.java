import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.TwoDirectionalSymmetricGraph;
import ru.geekbrains.task.ShortestDistance;

public class TaskTest {
    @Test
    // Balanced: 2812348 / 10000000 (28.123482%)
    void test1() {
        TwoDirectionalSymmetricGraph<String, Float> graph = new TwoDirectionalSymmetricGraph<>();

        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");


        graph.setEdge("1", "2", 7f);
        graph.setEdge("1", "3", 9f);
        graph.setEdge("1", "6", 14f);
        graph.setEdge("2", "3", 10f);
        graph.setEdge("2", "4", 15f);
        graph.setEdge("3", "6", 2f);
        graph.setEdge("3", "4", 11f);
        graph.setEdge("5", "6", 9f);
        graph.setEdge("5", "4", 6f);

        Assertions.assertEquals(20, ShortestDistance.Dijkstra(graph, "1", "5"));
    }

    @Test
    void test2() {
        TwoDirectionalSymmetricGraph<String, Float> graph = new TwoDirectionalSymmetricGraph<>();

        graph.addNode("Москва");
        graph.addNode("Сочи");
        graph.addNode("Нижний Новгород");
        graph.addNode("Санкт Петербург");
        graph.addNode("Красноярск");
        graph.addNode("Казань");
        graph.addNode("Уфа");
        graph.addNode("Владивосток");
        graph.addNode("Екатеринбург");
        graph.addNode("Омск");

        graph.addNode("Некоторый несвязный город");


        graph.setEdge("Москва", "Сочи", 140f);
        graph.setEdge("Москва", "Нижний Новгород", 70f);
        graph.setEdge("Москва", "Владивосток", 500f);
        //graph.setEdge("Москва", "Казань", 75f);

        graph.setEdge("Сочи", "Красноярск", 335f);
        graph.setEdge("Сочи", "Нижний Новгород", 155f);
        //graph.setEdge("Сочи", "Екатеринбург", 215f);

        graph.setEdge("Нижний Новгород", "Омск",290f);
        graph.setEdge("Нижний Новгород", "Екатеринбург",105f);

        graph.setEdge("Санкт Петербург", "Владивосток",510f);
        graph.setEdge("Санкт Петербург", "Казань",120f);
        //graph.setEdge("Санкт Петербург", "Уфа",150f);
        graph.setEdge("Санкт Петербург", "Красноярск",305f);

        graph.setEdge("Красноярск", "Омск", 120f);
        graph.setEdge("Красноярск", "Екатеринбург", 175f);

        //graph.setEdge("Казань","Омск",120f);
        graph.setEdge("Казань","Уфа",75f);

        graph.setEdge("Уфа","Владивосток",406f);
        graph.setEdge("Уфа","Омск",130f);

        //graph.setEdge("Владивосток","Екатеринбург",530f);

        System.out.println("Москва - Сочи: " + ShortestDistance.Dijkstra(graph, "Москва", "Сочи"));
        System.out.println("Москва - Нижний Новгород: " + ShortestDistance.Dijkstra(graph, "Москва", "Нижний Новгород"));
        System.out.println("Москва - Санкт Петербург: " + ShortestDistance.Dijkstra(graph, "Москва", "Санкт Петербург"));
        System.out.println("Москва - Красноярск: " + ShortestDistance.Dijkstra(graph, "Москва", "Красноярск"));
        System.out.println("Москва - Казань: " + ShortestDistance.Dijkstra(graph, "Москва", "Казань"));
        System.out.println("Москва - Уфа: " + ShortestDistance.Dijkstra(graph, "Москва", "Уфа"));
        System.out.println("Москва - Владивосток: " + ShortestDistance.Dijkstra(graph, "Москва", "Владивосток"));
        System.out.println("Москва - Екатеринбург: " + ShortestDistance.Dijkstra(graph, "Москва", "Екатеринбург"));
        System.out.println("Москва - Омск: " + ShortestDistance.Dijkstra(graph, "Москва", "Омск"));

        System.out.println("Москва - Некоторый недостижымий город: " + ShortestDistance.Dijkstra(graph, "Москва", "Некоторый недостижимый город"));

        Assertions.assertNull(ShortestDistance.Dijkstra(graph, "Москва", "Некоторый несвязный город"));
    }

    @Test
    void test3() {
        TwoDirectionalSymmetricGraph<String, Float> graph = new TwoDirectionalSymmetricGraph<>();

        graph.addNode("1");
        graph.addNode("2");
        graph.addNode("3");
        graph.addNode("4");
        graph.addNode("5");
        graph.addNode("6");


        graph.setEdge("1", "2", 1f);
        graph.setEdge("2", "3", 1f);
        graph.setEdge("3", "4", 1f);
        graph.setEdge("4", "5", 10f);
        graph.setEdge("5", "6", 20f);
        graph.setEdge("6", "1", 40f);

        Assertions.assertEquals(33, ShortestDistance.Dijkstra(graph, "1", "6"));
    }
}