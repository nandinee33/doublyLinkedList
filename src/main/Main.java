package main;

import defination.DoublyLinkedList;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list =
                new DoublyLinkedList<>();
        System.out.println(list.add(5));
        System.out.println(list.add(4));
        System.out.println(list.add(6));
        System.out.println(list);
        System.out.println(list.remove(2));
        System.out.println(list);

    }
}
