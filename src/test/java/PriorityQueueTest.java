import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;
import java.util.stream.Stream;

public class PriorityQueueTest {
    static Stream<Arguments> StringIntAndListProvider() {
        return Stream.of(
                Arguments.of(new int[]{3, 2, 1}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{2, -1, 5, 4}, new int[]{-1, 2, 4, 5}),
                Arguments.of(new int[]{5, -1, -3, 0}, new int[]{-3, -1, 0, 5}),
                Arguments.of(new int[]{3, -2, -5, 1, 2}, new int[]{-5, -2, 1, 2, 3}),
                Arguments.of(new int[]{3, 7, 2, -1, -2}, new int[]{-2, -1, 2, 3, 7})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("StringIntAndListProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array) {
        PriorityQueue<Integer> test = new PriorityQueue<>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];

        for (int i = 0; i < random_array.length; i++) {
            s = random_array[i];
            test.add(s);
        }
        for (int i = 0; i < random_array.length; i++) {
            s = test.poll();
            result[i] = s.intValue();
        }
        assertArrayEquals(correct_array, result);
    }

    @Test
    public void PriorityQueue_exceptionTest_a() {
        assertThrows(NullPointerException.class, () -> {
            PriorityQueue<String> test = new PriorityQueue<>();
            test.add(null);
        });
    }
    @Test
    public void PriorityQueue_exceptionTest_b() {
        class obj {
            int n;
            obj(int n) {
                this.n = n;
            }
        }
        assertThrows(ClassCastException.class, () -> {
            PriorityQueue<obj> test = new PriorityQueue<>();
            test.add(new obj(123));
        });
    }
    @Test
    public void PriorityQueue_exceptionTest_c(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<String> test = new PriorityQueue<>(0);
        });
    }

}
