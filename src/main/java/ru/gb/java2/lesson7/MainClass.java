package ru.gb.java2.lesson7;

public class MainClass {
    public static void main(String[] args) throws Exception {
        TwoDirectionalList<String> list1 = new TwoDirectionalList<>();
        System.out.println(list1);
        list1.insertToStart("a");
        System.out.println(list1);
        list1.insertToStart("b");
        System.out.println(list1);
        list1.insertToEnd("c");
        System.out.println(list1);
        list1.insertAt("1", 1);
        System.out.println(list1);
        list1.insertAt("3", 3);
        System.out.println(list1);
        list1.insertAt("4", 4);
        System.out.println(list1);
        list1.insertAt("6", 6);
        System.out.println(list1.insertAt("6", 6).getValue());
        System.out.println(list1);

        System.out.println(list1.getFirst().getValue());
        TwoDirectionalList<String>.TwoDirectionalNode<String> a = list1.getAt(3);
        System.out.println(a.getPrev().getValue());
        System.out.println(list1.getAt(3).getValue());
        System.out.println(list1.getAt(6).getValue());
        System.out.println(list1.getAt(7).getValue());

        list1.validate();

        list1.removeAt(6);
        System.out.println(list1);
        list1.removeAt(0);
        System.out.println(list1);
        list1.removeAt(3);
        System.out.println(list1);
        list1.remove("b");
        System.out.println(list1);
        list1.remove("a");
        System.out.println(list1);
        list1.remove("1");
        System.out.println(list1);
        list1.remove("1");
        System.out.println(list1);

        list1.validate();

        TwoDirectionalList<String> list2 = new TwoDirectionalList<>(new String[]{"a", "b", "c"});
        list2.validate();
        System.out.println(list2);

        OneDirectionalList<Integer> list3 = new OneDirectionalList<>();
        System.out.println(list3);
        OneDirectionalList<Integer> list4 = new OneDirectionalList<>(new Integer[]{1, 2, 3});
        System.out.println(list4);
    }
}


