package otus.algo.p06_mergesort;

import otus.algo.p04_inssort.InsertionSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static
    void merge(int[] copy, int begin, int middle, int end, int[] array) {
        // copy holds partially sorted data
        int i1 = begin;
        int i2 = middle;
        for (int i = begin; i < end; i++) {
            if (i1 < middle && (i2 >= end || copy[i1] <= copy[i2])) {
                array[i]=copy[i1];
                i1++;
            } else {
                array[i]= copy[i2];
                i2++;
            }
        }
    }

    public static
    void sort(int[] list) {
        // copy holds partially sorted data
        int[] copy = Arrays.copyOf(list, list.length);
        splitMerge(copy, 0, list.length, list);
    }

    public static
    void sortWithIns(int[] list) {
        // copy holds partially sorted data
        int[] copy = Arrays.copyOf(list, list.length);
        splitMergeIns(copy, 0, copy.length, list);
    }


    public static
    void splitMerge(int[]  copy, int begin, int end, int[] array) {
        // copy holds partially sorted data
        if (end - begin <= 1)
            return;

        int middle = (end + begin) / 2;
        splitMerge(array, begin, middle, copy);
        splitMerge(array, middle, end, copy);
        merge(copy, begin, middle, end, array);
    }

    public static <T extends Comparable<T>>
    void splitMergeIns(int[] copy, int begin, int end, int[] array) {
        // copy holds partially sorted data
        if (end - begin <= 1)
            return;
        if(end - begin < 128) {
            InsertionSort.sort(array, begin, end);
            return;
        }

        int middle = (end + begin) / 2;
        splitMergeIns(array, begin, middle, copy);
        splitMergeIns(array, middle, end, copy);
        merge(copy, begin, middle, end, array);
    }

}
