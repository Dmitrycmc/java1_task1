package ru.gb.java2.lesson7;

public interface DirectionalList<T> {
    Node<T> insertToStart(T val) throws Exception;
    Node<T> insertAt(T val, int index) throws Exception;
    boolean remove(T val);
    void removeAt(int index) throws Exception;
    Node<T> getFirst() throws Exception;
    Node<T> getAt(int index) throws Exception;
    int size();
}
