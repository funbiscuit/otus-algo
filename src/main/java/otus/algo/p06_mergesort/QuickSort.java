package otus.algo.p06_mergesort;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class QuickSort {
    private static final Random r = new Random();

    public static <T extends Comparable<T>>
    void sort(List<T> list) {

        quickSort(list, 0, list.size());
    }

    public static <T extends Comparable<T>>
    void quickSort(List<T> list, int begin, int end) {
        if (begin < end) {
            int p = partition(list, begin, end);
            quickSort(list, begin, p);
            quickSort(list, p + 1, end);
        }
    }

    public static <T extends Comparable<T>>
    int partition(List<T> list, int begin, int end) {

        int i = begin - 1;
        // extra randomization

        Collections.swap(list, r.nextInt(end - begin) + begin, end - 1);
        T pivot = list.get(end - 1);
        for (int j = begin; j < end - 1; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                ++i;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, end - 1);
        return i + 1;
    }


}
