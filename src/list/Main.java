package list;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        IntegerList list = new IntegerList(new LinkedList<>(Arrays.asList(1, 5, 8, 4, 999, 0, 50, 200, 99, 2, 900, 11, 20, 3, 60, 69, 51, 10000, 6, 13, 12)));
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 1000; i += 1) {
            list.add((int)(Math.random() * 12345 + 1));
        }
        list = new LinkedList<>(new HashSet<>(list));
        IntegerList integerList = new IntegerList(list);
        integerList.setMaxThreads(1);
        integerList.sort();
        System.out.println("Ordered list: " + list.toString());
    }
}
