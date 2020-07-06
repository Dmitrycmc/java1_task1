package ru.gb.java2.lesson7;

public class MainClass {
    public static void main(String[] args) throws Exception {
        OneDirectionalList<String> list = new OneDirectionalList<>();
        System.out.println(list);
        list.insertToStart("a");
        System.out.println(list);
        list.insertToStart("b");
        System.out.println(list);
        list.insertToStart("c");
        System.out.println(list);
        list.insertAt("1", 1);
        System.out.println(list);
        list.insertAt("3", 3);
        System.out.println(list);
        list.insertAt("4", 4);
        System.out.println(list);
        list.insertAt("6", 6);
        System.out.println(list);


        list.removeAt(6);
        System.out.println(list);
        list.removeAt(0);
        System.out.println(list);
        list.removeAt(3);
        System.out.println(list);
        list.remove("b");
        System.out.println(list);
        list.remove("a");
        System.out.println(list);
        list.remove("1");
        System.out.println(list);
        list.remove("1");
        System.out.println(list);
        list.removeAt(5);
        System.out.println(list);

    }
}


