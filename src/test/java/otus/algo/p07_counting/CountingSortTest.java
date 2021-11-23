package otus.algo.p07_counting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CountingSortTest {


    @Test
    void testSort() {
        Random random = new Random();
        random.setSeed(0);
        int maxVal = 99_999;
        int[] original = IntStream.range(1, 10_000_000)
                .map(i -> random.nextInt(maxVal))
                .toArray();

        // almost sorted data
//        int[] original = IntStream.range(1, 2_000_000)
//                .toArray();
//        for (int i = 0; i < original.length / 50; i++) {
//            int i1 = random.nextInt(original.length);
//            int i2 = random.nextInt(original.length);
//            int v = original[i1];
//            original[i1] = original[i2];
//            original[i2] = v;
//        }

        for (int i = 0; i < 10; i++) {
            int[] list = Arrays.copyOf(original, original.length);
            int[] copy = Arrays.copyOf(original, original.length);
            long start = System.currentTimeMillis();
            CountingSort.sort(list, maxVal);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop - start));
            Arrays.sort(copy);
            assertArrayEquals(copy, list);
        }

        for (int i = 0; i < 10; i++) {
            int[] list = Arrays.copyOf(original, original.length);
            long start = System.currentTimeMillis();
            Arrays.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (lib): " + (stop - start));
        }

    }

}