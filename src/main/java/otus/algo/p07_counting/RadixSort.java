package otus.algo.p07_counting;

public class RadixSort {

    public static void sort(int[] list, int digits) {
        int radix = 10;
        int[] counters = new int[radix];
        int radixNext = radix;
        int radixCurrent = 1;
        int[] copy = new int[list.length];
        for (int i = 0; i < digits; i++) {
            // sort on ith digit (counting from least significant)
            for (int j = 0; j < radix; j++) {
                counters[j] = 0;
            }

            for (int k = 0; k < list.length; k++) {
                int j = list[k];
                copy[k] = j;
                int digit = (j % radixNext) / radixCurrent;
                counters[digit]++;
            }
            for (int j = 1; j < counters.length; j++) {
                counters[j] += counters[j - 1];
            }
            for (int j = copy.length - 1; j >= 0; j--) {
                int digit = (copy[j] % radixNext) / radixCurrent;
                counters[digit]--;
                list[counters[digit]] = copy[j];
            }
            radixCurrent = radixNext;
            radixNext *= radix;
        }
    }

}
