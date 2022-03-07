import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.BinaryTree;
import ru.geekbrains.utils.MathUtils;

public class StructureTest {
    @Test
    // Balanced: 2812348 / 10000000 (28.123482%)
    void stack() {
        int n = 10000000;
        int maxDepth = 4;
        int balanced = 0;

        for (int i = 0; i < n; i++) {
            BinaryTree<Integer> tree = new BinaryTree<>();

            while (true) {
                int item = MathUtils.randomInt(-25, 25);

                if (tree.checkLayer(item) <= maxDepth) {
                    tree.add(item);
                } else {
                    break;
                }
            }

            if (tree.isBalanced()) {
                balanced++;
            }
        }

        System.out.println(String.format("Balanced: %d / %d (%f%%)", balanced, n, (float) 100 * balanced / n));
    }
}