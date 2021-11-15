package otus.algo.p02_basetypes;

import org.junit.jupiter.api.Test;

public class OListTest {
    @Test
    void test() {
        OList<Integer> l = new OList<>();
        for(int i=0; i<10; i++)
            l.add(i*i);

        OList.ListItem<Integer> li = l.head();
        while (li != null) {
            System.out.println(li.get());
            li = li.getNext();
        }
    }
}
