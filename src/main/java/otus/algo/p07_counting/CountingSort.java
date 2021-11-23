package otus.algo.p07_counting;

import java.util.Arrays;

public class CountingSort {


    /**
     * @param list
     * @param k    - maximum value of element in list
     */
    public static void sort(int[] list, int k) {
        int[] counters = new int[k + 1];

        int[] copy = Arrays.copyOf(list, list.length);
        for (int j : list) {
            counters[j]++;
        }
        for (int i = 1; i < counters.length; i++) {
            counters[i] += counters[i - 1];
        }
        for (int i = copy.length - 1; i >= 0; i--) {
            counters[copy[i]]--;
            list[counters[copy[i]]] = copy[i];
        }
    }

}
