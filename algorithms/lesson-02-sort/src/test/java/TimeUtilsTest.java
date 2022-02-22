import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.geekbrains.utils.TimeUtils;

public class TimeUtilsTest {
    @Test
    void TimeUtilsFormat1() {
        Assertions.assertEquals(TimeUtils.format(0), "0 ms");
    }

    @Test
    void TimeUtilsFormat2() {
        Assertions.assertEquals(TimeUtils.format(400), "400 ms");
    }

    @Test
    void TimeUtilsFormat3() {
        Assertions.assertEquals(TimeUtils.format(2400), "2 sec 400 ms");
    }

    @Test
    void TimeUtilsFormat4() {
        Assertions.assertEquals(TimeUtils.format(62400), "1 min 2 sec 400 ms");
    }

    @Test
    void TimeUtilsFormat5() {
        Assertions.assertEquals(TimeUtils.format(60400), "1 min 400 ms");
    }
}
