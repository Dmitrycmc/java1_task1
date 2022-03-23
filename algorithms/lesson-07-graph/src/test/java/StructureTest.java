import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.TwoDirectionalSymmetricGraph;
import ru.geekbrains.task.ShortestDistance;

public class StructureTest {
    @Test
    // Balanced: 2812348 / 10000000 (28.123482%)
    void graph() {
        TwoDirectionalSymmetricGraph<String, Float> graph = new TwoDirectionalSymmetricGraph<>();

        // This node not found: Piters
        // System.out.println(graph.getEdge("Moscow", "Piters"));
        // Values must be different: Piter, Piter
        // System.out.println(graph.getEdge("Piter", "Piter"));
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
}