package list;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IntegerList integerList0 = new IntegerList();
        integerList0.setMaxThreads(1);
        integerList0.sort();
        System.out.println("Ordered list de 1: " + integerList0.toString());
        System.out.println("Esta ordenada?: " + integerList0.checkIsSorted());
        System.out.println();

        IntegerList integerList1 = new IntegerList(Collections.singletonList(0));
        integerList1.setMaxThreads(1);
        integerList1.sort();
        System.out.println("Ordered list de 1: " + integerList1.toString());
        System.out.println("Esta ordenada?: " + integerList1.checkIsSorted());
        System.out.println();

        IntegerList integerList2 = new IntegerList(Arrays.asList(2, 1));
        integerList2.setMaxThreads(1);
        integerList2.sort();
        System.out.println("Ordered list de 2: " + integerList2.toString());
        System.out.println("Esta ordenada?: " + integerList2.checkIsSorted());
        System.out.println();

        IntegerList integerList3 = new IntegerList(Arrays.asList(1, 3, 2));
        integerList3.setMaxThreads(1);
        integerList3.sort();
        System.out.println("Ordered list de 3: " + integerList3.toString());
        System.out.println("Esta ordenada?: " + integerList3.checkIsSorted());
        System.out.println();

        IntegerList orderedList = new IntegerList(new LinkedList<>(Arrays.asList(1, 5, 10, 11, 13, 14, 15, 16, 17, 20)));
        orderedList.setMaxThreads(1);
        orderedList.sort();
        System.out.println("Ordered list: " + orderedList.toString());
        System.out.println("Esta ordenada?: " + orderedList.checkIsSorted());
        System.out.println();

        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < 1000; i += 1) {
            list.add((int)(Math.random() * 12345 + 1));
        }
        list = new LinkedList<>(new HashSet<>(list));
        IntegerList integerList = new IntegerList(list);
        integerList.setMaxThreads(10);
        integerList.sort();
        System.out.println("Ordered list de muchos: " + integerList.toString());
        System.out.println("Esta ordenada?: " + integerList.checkIsSorted());
        System.out.println();
    }
}
