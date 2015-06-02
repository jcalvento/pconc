package list;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        IntegerList integerList0 = new IntegerList();
        integerList0.setMaxThreads(2);
        integerList0.sort();
        System.out.println("Lista vacia: " + integerList0.toString());
        System.out.println("Esta ordenada?: " + integerList0.checkIsSorted());
        System.out.println();

        IntegerList integerList1 = new IntegerList(new ArrayList<>(Collections.singletonList(0)));
        integerList1.setMaxThreads(2);
        integerList1.sort();
        System.out.println("Lista de 1 elemento: " + integerList1.toString());
        System.out.println("Esta ordenada?: " + integerList1.checkIsSorted());
        System.out.println();

        IntegerList integerList2 = new IntegerList(Arrays.asList(2, 1));
        integerList2.setMaxThreads(2);
        integerList2.sort();
        System.out.println("Lista de 2 elemento: " + integerList2.toString());
        System.out.println("Esta ordenada?: " + integerList2.checkIsSorted());
        System.out.println();

        IntegerList integerList3 = new IntegerList(Arrays.asList(1, 3, 2));
        integerList3.setMaxThreads(2);
        integerList3.sort();
        System.out.println("Lista de 3 elemento: " + integerList3.toString());
        System.out.println("Esta ordenada?: " + integerList3.checkIsSorted());
        System.out.println();

        IntegerList orderedList = new IntegerList(new ArrayList<>(Arrays.asList(1, 5, 10, 11, 13, 14, 15, 16, 17, 20)));
        orderedList.setMaxThreads(2);
        orderedList.sort();
        System.out.println("Lista ya ordenada: " + orderedList.toString());
        System.out.println("Esta ordenada?: " + orderedList.checkIsSorted());
        System.out.println();

        System.out.println("Agrego un elemento a una lista ya ordenada, manteniendo el orden");
        orderedList.add(21);
        orderedList.sort();
        System.out.println("Lista: " + orderedList.toString());
        System.out.println("Esta ordenada?: " + orderedList.checkIsSorted());
        System.out.println();

        System.out.println("Agrego un elemento a una lista ya ordenada, provocando que ordene de vuelta");
        orderedList.add(6);
        orderedList.sort();
        System.out.println("Lista: " + orderedList.toString());
        System.out.println("Esta ordenada?: " + orderedList.checkIsSorted());
        System.out.println();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 200; i += 1) {
            list.add((int)(Math.random() * 12345 + 1));
        }
        list = new ArrayList<>(new HashSet<>(list));
        IntegerList integerList = new IntegerList(list);
        integerList.setMaxThreads(16);
        System.out.println("Lista original: " + integerList.toString());
        integerList.sort();
        System.out.println("Lista de muchos random: " + integerList.toString());
        System.out.println("Esta ordenada?: " + integerList.checkIsSorted());
        System.out.println();
    }
}
