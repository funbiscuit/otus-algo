package otus.algo.p05_heapsort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapSortTest {
    @Test
    void testSort1() {
        List<Integer> original = List.of(0, 5, -2, 9, 2, 3, 1, 4, 5, 8);
        List<Integer> list = new ArrayList<>(original);
        Heap<Integer> heap = new Heap<>(list);
        assertEquals(original.stream().sorted().collect(Collectors.toList()), heap.sorted());
    }

    @Test
    void testSort2() {
        List<Integer> original = List.of(11, 6, 10, 1, 9, 15, 7, 8, 17, 5, 2);
        List<Integer> list = new ArrayList<>(original);
        Heap<Integer> heap = new Heap<>(list);
        assertEquals(original.stream().sorted().collect(Collectors.toList()), heap.sorted());
    }

    @Test
    void testSort3() {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 100_000)
                .mapToObj(i -> random.nextInt(i) - i/2)
                .collect(Collectors.toList());
        List<Integer> list = new ArrayList<>(original);
        long start = System.currentTimeMillis();
        Heap<Integer> heap = new Heap<>(list);
        heap.sorted();
        long stop = System.currentTimeMillis();
        System.out.println("Millis: " + (stop-start));
        assertEquals(original.stream().sorted().collect(Collectors.toList()), heap.getObjects());
    }

    @Test
    void testBuildHeap() {
        List<Integer> list = new ArrayList<>(List.of(11, 6, 10, 1, 9, 15, 7, 8, 17, 5, 2));
        Heap<Integer> heap = new Heap<>(list);
        assertEquals(List.of(17, 11, 15, 8, 9, 10, 7, 6, 1, 5, 2), heap.getObjects());
    }

    @Test
    void testRemove() {
        List<Integer> list = new ArrayList<>(List.of(11, 6, 10, 1, 9, 15, 7, 8, 17, 5, 2));
        Heap<Integer> heap = new Heap<>(list);
        heap.removeAt(3);
        assertEquals(List.of(17, 11, 15, 6, 9, 10, 7, 2, 1, 5), heap.getObjects());

        heap.removeAt(1);
        assertEquals(List.of(17, 9, 15, 6, 5, 10, 7, 2, 1), heap.getObjects());
    }
}