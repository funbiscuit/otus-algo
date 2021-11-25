package otus.algo.p08_order_stat;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatTest {

    @Test
    void test() {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1,1_000_000)
                .mapToObj(random::nextInt)
                .collect(Collectors.toList());

        int order = 155;

        int avg = 0;
        int avgCount = 0;
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            int elem = OrderStat.select(list, order);
            long stop = System.currentTimeMillis();
            System.out.println("Millis: " + (stop - start));

            if (i > 1) {
                avg += stop - start;
                ++avgCount;
            }
            assertEquals(original.stream().sorted().collect(Collectors.toList()).get(order),
                    elem);
        }
        System.out.println("Millis (avg): " + (avg / avgCount));
        avg = 0;
        avgCount = 0;
        for (int i = 0; i < 10; i++) {
            List<Integer> list = new ArrayList<>(original);
            long start = System.currentTimeMillis();
            list.sort(null);
            long stop = System.currentTimeMillis();
            System.out.println("Millis (sort): " + (stop - start));

            if (i > 1) {
                avg += stop - start;
                ++avgCount;
            }
        }
        System.out.println("Millis (avg): " + (avg / avgCount));
    }

}
