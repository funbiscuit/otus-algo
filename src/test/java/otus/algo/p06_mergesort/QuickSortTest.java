package otus.algo.p06_mergesort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void testSort3() {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 1_000_000)
                .mapToObj(i -> random.nextInt(i) - i/2)
                .collect(Collectors.toList());
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            QuickSort.sort(list);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop-start));
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