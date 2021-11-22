package otus.algo.p04_inssort;

import org.junit.jupiter.api.Test;
import otus.algo.p06_mergesort.QuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void testSort() {
        Random random = new Random();
        random.setSeed(0);
        int[] original = IntStream.range(1, 1000)
                .map(i -> random.nextInt(i) - i/2)
                .toArray();
        for (int i = 0; i < 10; i++) {
            int[] list = Arrays.copyOf(original, original.length);
            int[] copy = Arrays.copyOf(original, original.length);
            long start = System.currentTimeMillis();
            InsertionSort.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop-start));
            Arrays.sort(copy);
            assertArrayEquals(copy, list);
        }

        for (int i = 0; i < 10; i++) {
            int[] list = Arrays.copyOf(original, original.length);
            long start = System.currentTimeMillis();
            Arrays.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (lib): " + (stop-start));
        }

    }
}