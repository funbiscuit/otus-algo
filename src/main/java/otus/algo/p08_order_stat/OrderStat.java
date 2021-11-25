package otus.algo.p08_order_stat;

import otus.algo.p04_inssort.InsertionSort;
import otus.algo.p06_mergesort.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class OrderStat {

    public static <T extends Comparable<T>>
    T select(List<T> list, int i) {
        return select(list, i, 0, list.size());
    }

    public static <T extends Comparable<T>>
    T select(List<T> list, int i, int begin, int end) {
        if (i < begin || i >= end) {
            throw new IllegalArgumentException("Index out of bounds");
        }

        if (end - begin <= 32) {
            InsertionSort.sort(list, begin, end);
            return list.get(i);
        }

        List<T> medians = new ArrayList<>((end - begin) / 5 + 2);

        for (int j = begin; j < end; j += 5) {
            int jEnd = Math.min(j + 5, end);
            InsertionSort.sort(list, j, jEnd);
            medians.add(list.get((j + jEnd) / 2));
        }

        T median = select(medians, medians.size() / 2, 0, medians.size());
        int index = 0;
        for (int j = begin; j < end; j += 5) {
            int jEnd = Math.min(j + 5, end);
            if (list.get((j + jEnd) / 2) == median) {
                index = (j + jEnd) / 2;
                break;
            }
        }

        int medianIndex = QuickSort.partitionWith(list, begin, end, index);

        if (i == medianIndex) {
            return median;
        } else if (i < medianIndex) {
            return select(list, i, begin, medianIndex);
        } else {
            return select(list, i, medianIndex + 1, end);
        }
    }
}
