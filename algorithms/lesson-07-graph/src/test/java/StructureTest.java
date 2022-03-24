import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.TwoDirectionalSymmetricGraph;
import ru.geekbrains.task.ShortestDistance;

public class StructureTest {
    @Test
    // Balanced: 2812348 / 10000000 (28.123482%)
    void graph() {
        TwoDirectionalSymmetricGraph<String, Float> graph = new TwoDirectionalSymmetricGraph<>();

        graph.addNode("Moscow");
        graph.addNode("Piter");

        // This node not found: Piters
        Assertions.assertThrows(RuntimeException.class, () -> {
            graph.setEdge("Moscow", "Piters", 12f);
        });
        Assertions.assertThrows(RuntimeException.class, () -> {
            graph.getEdge("Moscow", "Piters");
        });
        // Values must be different: Piter, Piter
        Assertions.assertThrows(RuntimeException.class, () -> {
            graph.setEdge("Piter", "Piter", 12f);
        });
        // Values must be different: Piter, Piter
        Assertions.assertThrows(RuntimeException.class, () -> {
            graph.getEdge("Piter", "Piter");
        });

        Assertions.assertNull(graph.getEdge("Moscow", "Piter"));
        graph.setEdge("Moscow", "Piter", 12f);
        Assertions.assertEquals(12f, graph.getEdge("Moscow", "Piter"));
    }
}