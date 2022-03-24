package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph<T, W> {
    List<T> nodes = new ArrayList<>();

    protected void _checkSetEdgeArgs(int i, int j) {
        if (i == j) {
            throw new RuntimeException("Value indices must be different: " + i + ", " + j);
        }
    }

    public int indexOfNode(T value) {
        if (!nodes.contains(value)) {
            throw new RuntimeException("This node not found: " + value);
        }

        return nodes.indexOf(value);
    }

    protected void _checkSetEdgeArgs(T v1, T v2) {
        if (v1.equals(v2)) {
            throw new RuntimeException("Values must be different: " + v1 + ", " + v2);
        }

        if (!nodes.contains(v1)) {
            throw new RuntimeException("This node not found: " + v1);
        }

        if (!nodes.contains(v2)) {
            throw new RuntimeException("This node not found: " + v2);
        }
    }

    public List<T> getNeighbours(T value) {
        List<T> result = new ArrayList<>();

        for (T node: nodes) {
            if (!node.equals(value)) {
                if (getEdge(node, value) != null) {
                    result.add(node);
                }
            }
        }

        return result;
    }

    public List<Integer> getNeighbours(int i) {
        List<Integer> result = new ArrayList<>();

        for (int j = 0; j < nodes.size(); j++) {
            if (i != j) {
                if (getEdge(i, j) != null) {
                    result.add(j);
                }
            }
        }

        return result;
    }

    abstract int addNode(T value);

    abstract W getEdge(int i, int j);

    abstract W getEdge(T v1, T v2);

    abstract void setEdge(int i, int j, W weight);

    abstract void setEdge(T v1, T v2, W weight);
}
