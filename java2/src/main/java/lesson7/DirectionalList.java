package lesson7;

interface DirectionalList<T> {
    Node<T> insertToStart(T value) throws Exception;
    Node<T> insertAt(T value, int index) throws Exception;
    boolean remove(T value);
    void removeAt(int index) throws Exception;
    Node<T> getFirst() throws Exception;
    Node<T> getAt(int index) throws Exception;
    int getSize();
}
