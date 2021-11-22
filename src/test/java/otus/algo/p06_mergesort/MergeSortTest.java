package otus.algo.p06_mergesort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {


    @Test
    void testMerge() {
        int[] original = IntStream.of(3, 6, 8, 11, 22, 4, 5, 9, 13).toArray();
        int[] list = Arrays.copyOf(original, original.length);
        int[] copy = Arrays.copyOf(original, original.length);
        MergeSort.merge(copy, 0, 5, 9, list);
        Arrays.sort(original);
        assertArrayEquals(original, list);
    }

    @Test
    void testSort() {
        int[] original = IntStream.of(5, 1, 0, -2, 7, 2, 7, 9, -11).toArray();
        int[] list = Arrays.copyOf(original, original.length);
        MergeSort.sort(list);
        Arrays.sort(original);
        assertArrayEquals(original, list);
    }

    @Test
    void testSort3() {
        Random random = new Random();
        random.setSeed(0);
        int[] original = IntStream.range(1, 2_000_000)
                .map(i -> random.nextInt(i) - i/2)
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
            MergeSort.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop - start));
            Arrays.sort(copy);
            assertArrayEquals(copy, list);
        }

        for (int i = 0; i < 10; i++) {
            int[] list = Arrays.copyOf(original, original.length);
            int[] copy = Arrays.copyOf(original, original.length);
            long start = System.currentTimeMillis();
            MergeSort.sortWithIns(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (with ins): " + (stop - start));
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
