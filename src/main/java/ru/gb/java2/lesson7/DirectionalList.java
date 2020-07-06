package ru.gb.java2.lesson7;

public interface DirectionalList<T> {
    Node<T> insertToStart(T val) throws Exception;
    Node<T> insertAt(T val, int index) throws Exception;
    boolean remove(T val);
    boolean removeAt(T val);
    Node<T> getFirst();
    Node<T> getAt();
    int size();
}
