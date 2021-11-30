package otus.algo.p09_treap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AVLTreeTest {


    @Test
    void testInsert() {
        AVLTree<Integer> tree = new AVLTree<>();

        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 200)
                .mapToObj(i -> random.nextInt(i) - i / 2)
                .collect(Collectors.toList());


        original.forEach(tree::insert);

        original.sort(null);

        List<Integer> sorted = new ArrayList<>();
        tree.inorder(sorted::add);
        assertEquals(original, sorted);
    }

    @Test
    void testRemove() {
        AVLTree<Integer> tree = new AVLTree<>();

        Random random = new Random();
        random.setSeed(0);
        List<Integer> original = IntStream.range(1, 200)
                .mapToObj(random::nextInt)
                .collect(Collectors.toList());
        original.forEach(tree::insert);
        original.sort(null);

//        tree.inorder(System.out::println);

        while (original.size() > 0) {
            Integer item = original.remove(random.nextInt(original.size()));
            tree.remove(item);
            List<Integer> sorted = new ArrayList<>();
            tree.inorder(sorted::add);
            assertEquals(original, sorted);
        }
    }

}