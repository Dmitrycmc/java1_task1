import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.BinaryTree;
import ru.geekbrains.utils.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class StructureTest {
    @Test
    void stack() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(MathUtils.randomInt(-25, 25));
        }

        BinaryTree<Integer> tree1 = BinaryTree.buildBalancedTree(list);

        System.out.println(tree1.getMaxDepth());
        System.out.println(tree1.getSize());
        System.out.println(tree1.isBalanced());
        System.out.println(tree1);

        BinaryTree<Integer> tree2 = BinaryTree.buildTree(list);

        System.out.println(tree2.getMaxDepth());
        System.out.println(tree2.getSize());
        System.out.println(tree2.isBalanced());
        System.out.println(tree2);
    }
}