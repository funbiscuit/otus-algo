package otus.algo.p06_mergesort;

import org.junit.jupiter.api.Test;
import otus.algo.p05_heapsort.Heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSortTest {


    @Test
    void testMerge() {
        List<Integer> original = List.of(3, 6, 8, 11, 22, 4, 5, 9, 13);
        List<Integer> list = new ArrayList<>(original);
        ArrayList<Integer> copy = new ArrayList<>(list);
        MergeSort.merge(copy, 0, 5, 9, list);
        assertEquals(original.stream().sorted().collect(Collectors.toList()), list);
    }

    @Test
    void testSort() {
        List<Integer> original = List.of(5, 1, 0, -2, 7, 2, 7, 9, -11);
        List<Integer> list = new ArrayList<>(original);
        MergeSort.sort(list);
        assertEquals(original.stream().sorted().collect(Collectors.toList()), list);
    }

    @Test
    void testSort3() {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 1_000_000)
                .mapToObj(i -> random.nextInt(i) - i/2)
                .collect(Collectors.toList());


//        List<Integer> original = IntStream.range(1, 1_000_000)
//                .boxed()
//                .collect(Collectors.toList());

        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            MergeSort.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop-start));
            assertEquals(original.stream().sorted().collect(Collectors.toList()), list);
        }

        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            MergeSort.sortWithIns(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (with ins): " + (stop-start));
            assertEquals(original.stream().sorted().collect(Collectors.toList()), list);
        }

        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            list.sort(null);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (lib): " + (stop-start));
        }

    }
}
