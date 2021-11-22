package otus.algo.p05_heapsort;

import java.util.Collections;
import java.util.List;

public class Heap<T extends Comparable<T>> {
    private List<T> objects;

    public Heap(List<T> objects) {
        this.objects = objects;
        buildHeap();
    }

    public List<T> sorted() {
        int size = objects.size();
        while (size > 1) {
            --size;
            Collections.swap(objects, 0, size);
            drown(0, size);
        }
        return objects;
    }

    public List<T> getObjects() {
        return objects;
    }

    /**
     * Remove element at specific index and return it
     *
     * @param i
     */
    public T removeAt(int i) {
        T element = objects.get(i);

        objects.set(i, objects.remove(objects.size() - 1));

        drown(i, objects.size());
        raise(i);
        return element;
    }

    /**
     * Converts objects list to non-increasing heap
     */
    private void buildHeap() {
        for (int i = (objects.size() - 1) / 2; i >= 0; i--) {
            drown(i, objects.size());
        }
    }

    /**
     * Drowns element at position i, assuming heap is of size 'size'
     * After calling this method, at i we receive correct heap
     *
     * @param i
     * @param size
     */
    private void drown(int i, int size) {
        while (true) {
            int left = leftChild(i);
            int right = rightChild(i);
            int max = i;

            if (left < size && objects.get(left).compareTo(objects.get(max)) > 0) {
                max = left;
            }
            if (right < size && objects.get(right).compareTo(objects.get(max)) > 0) {
                max = right;
            }
            if (max == i) {
                break;
            }
            Collections.swap(objects, max, i);
            i = max;
        }
    }

    private void raise(int i) {
        while (i > 0 && objects.get(i).compareTo(objects.get(parent(i))) > 0) {
            Collections.swap(objects, i, parent(i));
            i = parent(i);
        }
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static int rightChild(int i) {
        return 2 * i + 2;
    }
}
