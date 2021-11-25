package otus.algo.p04_inssort;

import java.util.List;

public class InsertionSort {

    public static void sort(int[] list) {
        sort(list, 0, list.length);
    }

    public static void sort(int[] list, int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            int item = list[i];
            int j = i - 1;
            while (j >= begin && list[j] > item) {
                list[j + 1] = list[j];
                --j;
            }
            list[j + 1] = item;
        }
    }

    public static <T extends Comparable<T>>
    void sort(List<T> list) {
        sort(list, 0, list.size());
    }

    public static <T extends Comparable<T>>
    void sort(List<T> list, int begin, int end) {
        for (int i = begin + 1; i < end; i++) {
            T item = list.get(i);
            int j = i - 1;
            while (j >= begin && list.get(j).compareTo(item) > 0) {
                list.set(j + 1, list.get(j));
                --j;
            }
            list.set(j + 1, item);
        }
    }
}
