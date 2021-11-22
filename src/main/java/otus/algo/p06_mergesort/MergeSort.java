package otus.algo.p06_mergesort;

import otus.algo.p04_inssort.InsertionSort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {
    public static <T extends Comparable<T>>
    void merge(List<T> copy, int begin, int middle, int end, List<T> array) {
        // copy holds partially sorted data
        int i1 = begin;
        int i2 = middle;
        for (int i = begin; i < end; i++) {
            if (i1 < middle && (i2 >= end || copy.get(i1).compareTo(copy.get(i2)) <= 0)) {
                array.set(i, copy.get(i1));
                i1++;
            } else {
                array.set(i, copy.get(i2));
                i2++;
            }
        }
    }

    public static <T extends Comparable<T>>
    void sort(List<T> list) {
        // copy holds partially sorted data
        ArrayList<T> copy = new ArrayList<>(list);
        splitMerge(copy, 0, copy.size(), list);
    }

    public static <T extends Comparable<T>>
    void sortWithIns(List<T> list) {
        // copy holds partially sorted data
        ArrayList<T> copy = new ArrayList<>(list);
        splitMergeIns(copy, 0, copy.size(), list);
    }


    public static <T extends Comparable<T>>
    void splitMerge(List<T> copy, int begin, int end, List<T> array) {
        // copy holds partially sorted data
        if (end - begin <= 1)
            return;

        int middle = (end + begin) / 2;
        splitMerge(array, begin, middle, copy);
        splitMerge(array, middle, end, copy);
        merge(copy, begin, middle, end, array);
    }

    public static <T extends Comparable<T>>
    void splitMergeIns(List<T> copy, int begin, int end, List<T> array) {
        // copy holds partially sorted data
        if (end - begin <= 1)
            return;
        if(end - begin < 16) {
            InsertionSort.sort(array, begin, end);
            return;
        }

        int middle = (end + begin) / 2;
        splitMergeIns(array, begin, middle, copy);
        splitMergeIns(array, middle, end, copy);
        merge(copy, begin, middle, end, array);
    }

}
