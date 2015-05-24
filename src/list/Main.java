package list;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IntegerList list = new IntegerList(new LinkedList<>(Arrays.asList(1, 5, 8, 20, 125, 999, 0, 50, 200, 99, 2, 900, 11)));
        list.maxThreads(2);
        list.sort();
        System.out.println("Ordered list: " + list.toString());
    }
}
