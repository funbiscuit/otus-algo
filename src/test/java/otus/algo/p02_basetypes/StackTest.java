package otus.algo.p02_basetypes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StackTest {
    @Test
    void testArrayList() {
        List<Integer> stackArray = new ArrayList<>();

        int testSize = 1_000_000;
        long start,stop;

        for(int k =0;k<10;k++){
            stackArray.clear();


            start = System.nanoTime();
            for(int i=0; i<testSize; i++)
                stackArray.add(i,i*i);
            for(int i=0; i<testSize; i++)
                stackArray.remove(stackArray.size()-1);
            stop = System.nanoTime();

            System.out.println("array " + (stop-start)/1000000.0);
        }
    }
    @Test
    void testLinkedList() {
        List<Integer> stackList = new LinkedList<>();

        int testSize = 1_000_000;
        long start,stop;

        for(int k =0;k<10;k++){
            stackList.clear();


            start = System.nanoTime();
            for(int i=0; i<testSize; i++)
                stackList.add(i,i*i);
            for(int i=0; i<testSize; i++)
                stackList.remove(stackList.size()-1);
            stop = System.nanoTime();

            System.out.println("list " + (stop-start)/1000000.0);
        }
    }
}
