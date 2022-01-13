package ru.gb.star.pool;

import java.util.ArrayList;
import java.util.List;

public abstract class Pool<T> {
    protected List<T> activeList;
    protected List<T> inactiveList;

    public T getActiveElementAt(int i) {
        return activeList.get(i);
    }

    public int getActiveElementsCount() {
        return activeList.size();
    }

    protected abstract T newObject();

    public void deactivate(T el) {
        if (activeList.contains(el)) {
            activeList.remove(el);
            inactiveList.add(el);
        }
    }

    public Pool() {
        this.activeList = new ArrayList<T>();
        this.inactiveList = new ArrayList<T>();
    }

    public T getInactiveElement() {
        T temp = inactiveList.size() > 0 ?
                inactiveList.remove(inactiveList.size() - 1) :
                newObject();

        activeList.add(temp);
        return temp;
    }
}
