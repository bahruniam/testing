import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

 class MinFinderTest {

    @Test
    public void testScenario1() {
        assertEquals(1, MinFinder.findMin(1, 2, 3));
    }

    @Test
    public void testScenario2() {
        assertEquals(-3, MinFinder.findMin(-1, -2, -3));
    }

    @Test
    public void testScenario3() {
        assertEquals(0, MinFinder.findMin(0, 0, 1));
    }
}