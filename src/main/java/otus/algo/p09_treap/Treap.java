package otus.algo.p09_treap;

import lombok.AllArgsConstructor;

import java.util.Random;
import java.util.function.Consumer;

@AllArgsConstructor
public class Treap<T extends Comparable<T>> {
    private T key;
    private int priority;

    private Treap<T> left;
    private Treap<T> right;

    private static final Random r = new Random(1110);

    public Treap(T key) {
        this.key = key;
        this.priority = r.nextInt();
    }

    public void inorder(Consumer<T> consumer) {
        if (left != null) {
            left.inorder(consumer);
        }
        consumer.accept(key);
        if (right != null) {
            right.inorder(consumer);
        }
    }

    /**
     * Merge two treaps. All elements in the left must be less than in the right
     *
     * @param left
     * @param right
     * @param <K>
     * @return
     */
    public static <K extends Comparable<K>> Treap<K> merge(Treap<K> left, Treap<K> right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        if (left.key == right.key) {
            if (right.left != null || left.right != null) {
                throw new IllegalStateException("Can't merge two overlapping treaps");
            }

            if (left.priority < right.priority) {
                return new Treap<>(right.key, right.priority, left, right.right);
            } else {
                Treap<K> r = right.right;
                right.right = null;
                return new Treap<>(left.key, left.priority, merge(left.left, right), r);
            }
        }

        if (left.priority > right.priority) {
            return new Treap<>(left.key, left.priority, left.left, merge(left.right, right));
        } else {
            return new Treap<>(right.key, right.priority, merge(left, right.left), right.right);
        }
    }

    /**
     * Splits given treap on provided key and returns
     * pair of two treaps. left treap contains <= key and right >key
     *
     * @param treap
     * @param key
     * @param <K>
     * @return
     */
    public static <K extends Comparable<K>> TreapPair<Treap<K>> split(Treap<K> treap, K key) {
        return split(treap, key, true);
    }

    public static <K extends Comparable<K>> TreapPair<Treap<K>> split(Treap<K> treap, K key, boolean includeEqual) {
        if (treap == null) {
            return null;
        }

        int cmp = key.compareTo(treap.key);

        if (cmp < 0) {
            TreapPair<Treap<K>> res = split(treap.left, key, includeEqual);
            treap.left = null;

            if (res == null) {
                return new TreapPair<>(null, treap);
            } else {
                return new TreapPair<>(res.left, merge(res.right, treap));
            }

        } else if (cmp > 0) {
            TreapPair<Treap<K>> res = split(treap.right, key, includeEqual);
            treap.right = null;

            if (res == null) {
                return new TreapPair<>(treap, null);
            } else {
                return new TreapPair<>(merge(treap, res.left), res.right);
            }
        } else {
            if (includeEqual) {
                Treap<K> right = treap.right;
                treap.right = null;
                return new TreapPair<>(treap, right);
            } else {
                TreapPair<Treap<K>> res = split(treap.left, key, false);
                if (res == null) {
                    return new TreapPair<>(null, treap.right);
                } else {
                    return new TreapPair<>(res.left, treap.right);
                }
            }
        }
    }

    public static <K extends Comparable<K>> Treap<K> add(Treap<K> treap, K key) {
        TreapPair<Treap<K>> res = split(treap, key);

        Treap<K> newTreap = new Treap<>(key);

        if (res.left == null) {
            return merge(newTreap, res.right);
        }

        Treap<K> merged = merge(res.left, newTreap);
        return merge(merged, res.right);
    }

    public Treap<T> add(T key) {
        TreapPair<Treap<T>> res = split(this, key);

        Treap<T> newTreap = new Treap<>(key);

        if (res.left == null) {
            return merge(newTreap, res.right);
        }

        Treap<T> merged = merge(res.left, newTreap);
        return merge(merged, res.right);
    }

    public Treap<T> remove(T key) {
        TreapPair<Treap<T>> res = split(this, key, true);

        if (res.left == null) {
            return res.right;
        }
        TreapPair<Treap<T>> res2 = split(res.left, key, false);

        return merge(res2.left, res.right);
    }

    public int getHeight() {
        // O(N) complexity
        int h = 1;
        if (left != null) {
            h = 1 + left.getHeight();
        }
        if (right != null) {
            h = Math.max(h, 1 + right.getHeight());
        }
        return h;
    }

    @AllArgsConstructor
    public static class TreapPair<K> {
        public K left;
        public K right;

    }
}
