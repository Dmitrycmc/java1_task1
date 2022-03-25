import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.structure.JavaTable;
import ru.geekbrains.structure.ListTable;
import ru.geekbrains.structure.HashTableOpenAddressing;
import ru.geekbrains.structure.HashTableСhaining;
import ru.geekbrains.structure.Table;

import java.util.UUID;

public class StructureTest {
    public static final int n = 10_000;

    void test(Table<String, String> table) {
        for (int i = 0; i < n; i++) {
            table.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());

            String key = UUID.randomUUID().toString();
            String value1 = UUID.randomUUID().toString();
            String value2 = UUID.randomUUID().toString();

            table.put(key, value1);
            Assertions.assertEquals(value1, table.get(key));

            table.put(key, value2);
            Assertions.assertEquals(value2, table.get(key));
            Assertions.assertTrue(table.containsKey(key));
            Assertions.assertTrue(table.containsValue(value2));

            Assertions.assertTrue(table.delete(key));
            Assertions.assertFalse(table.containsKey(key));

            Assertions.assertNull(table.get(key));
            Assertions.assertFalse(table.delete(key));

            Assertions.assertEquals(i + 1, table.size());
        }

    }

    @Test
    void hashTableChaining() {
        test(new HashTableСhaining<>());
        // 1.865s
    }

    @Test
    void hashTableOpenAddressing() {
        test(new HashTableOpenAddressing<>());
        // 0.490s
    }

    @Test
    void javaTable() {
        test(new JavaTable<>());
        // 0.557s
    }

    @Test
    void listTable() {
        test(new ListTable<>(n));
        // 11.758s
    }
}