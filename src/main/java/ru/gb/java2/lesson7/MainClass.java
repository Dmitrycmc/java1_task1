package ru.gb.java2.lesson7;

public class MainClass {
    public static void main(String[] args) throws Exception {
        OneDirectionalList<String> list = new OneDirectionalList<>();
        System.out.println(list);
        list.insertToStart("Ку");
        System.out.println(list);
        list.insertToStart("rf");
        System.out.println(list);
        list.insertToStart("r12f");
        System.out.println(list);
        list.insertAt("1", 1);
        System.out.println(list);
        list.insertAt("3", 3);
        System.out.println(list);
        list.insertAt("4", 4);
        System.out.println(list);
        list.insertAt("6", 6);
        System.out.println(list);

    }
}


