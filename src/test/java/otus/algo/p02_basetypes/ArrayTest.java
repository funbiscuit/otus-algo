package otus.algo.p02_basetypes;

import org.junit.jupiter.api.Test;

import java.util.Date;

class ArrayTest {
    @Test
    void test() {
        DArray<Integer> a = new DArray<>();

        for (int i = 0; i < 10; i++)
            a.add(i, i * i);

        for (int i = 0; i < 10; i++)
            System.out.println(a.get(i));
    }

    @Test
    void benchDArray() {
        DArray<Integer> a = new DArray<>();

        int testSize = 10_000;
        long start, stop;

        start = System.nanoTime();
        for (int i = 0; i < testSize; i++)
            a.add(i, i * i);
        stop = System.nanoTime();

        System.out.println("Darray, ms " + (stop - start) / 1_000_000.0);
    }

    @Test
    void benchBArray() {
        BArray<Integer> b = new BArray<>(1000, 1000);

        int testSize = 100_000;
        long start, stop;

        start = System.nanoTime();
        for (int i = 0; i < testSize; i++)
            b.add(i, i * i);
        stop = System.nanoTime();

        System.out.println("Barray, ms " + (stop - start) / 1_000_000.0);
    }

    @Test
    void benchIArray() {
        IArray<Integer> iArray = new IArray<>(1000);

        int testSize = 100_000;
        long start, stop;

        start = System.nanoTime();
        for (int i = 0; i < testSize; i++)
            iArray.add(i, i * i);
        stop = System.nanoTime();

        System.out.println("Iarray, ms " + (stop - start) / 1_000_000.0);
    }
}