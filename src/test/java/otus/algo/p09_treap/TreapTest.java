package otus.algo.p09_treap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreapTest {

    @Test
    void buildTreap() {
        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 200)
                .mapToObj(random::nextInt)
                .collect(Collectors.toList());

        Treap<Integer> treap = new Treap<>(original.get(0));
        for (int i = 0; i < original.size(); i++) {
            if (i == 0)
                continue;

            treap = treap.add(original.get(i));

        }

        original.sort(null);

        List<Integer> ints = new ArrayList<>();
        treap.inorder(ints::add);

        assertEquals(original, ints);
    }

    @Test
    void removeElements() {

        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 27)
                .mapToObj(random::nextInt)
                .collect(Collectors.toList());

        Treap<Integer> treap = new Treap<>(original.get(0));
        for (int i = 0; i < original.size(); i++) {
            if (i == 0)
                continue;

            treap = treap.add(original.get(i));
        }

        original.sort(null);

        for (Integer num : original.stream().distinct().collect(Collectors.toList())) {

            treap = treap.remove(num);
            original.removeIf(num::equals);

            List<Integer> ints = new ArrayList<>();
            if (treap != null) {
                treap.inorder(ints::add);
            } else {
                assertEquals(0, original.size());
                break;
            }
            System.out.println(ints);
            assertEquals(original, ints);
        }
    }

    @Test
    void testHeight() {
        Treap<Integer> treap = new Treap<>(1);

        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 1000)
                .mapToObj(random::nextInt)
                .collect(Collectors.toList());

        int prevH = 0;
        for (int i = 0; i < original.size(); i++) {
            treap = treap.add(original.get(i));
            int h = treap.getHeight();
            if (h != prevH) {

                System.out.printf("%d\t%d\n", i + 2, h);
                prevH = h;
            }
        }
    }
}