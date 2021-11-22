package otus.algo.p04_inssort;

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
}
