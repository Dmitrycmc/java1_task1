package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoDirectionalGraph<T, W> extends Graph<T, W> {
    List<List<W>> weightsL = new ArrayList<>();
    List<List<W>> weightsU = new ArrayList<>();

    private W _getEdge(int i, int j) {
        return i < j ? weightsU.get(i).get(j) : weightsL.get(i).get(j);
    }

    @Override
    public W getEdge(int i, int j) {
        _checkSetEdgeArgs(i, j);

        return _getEdge(i, j);
    }

    @Override
    public W getEdge(T v1, T v2) {
        _checkSetEdgeArgs(v1, v2);

        int i = indexOfNode(v1);
        int j = indexOfNode(v2);

        return _getEdge(i, j);
    }

    @Override
    public int addNode(T value) {
        if (nodes.contains(value)) {
            throw new RuntimeException("This node already exists: " + value);
        }

        int size = nodes.size();
        nodes.add(value);
        weightsL.add(new ArrayList<>(Collections.nCopies(size, null)));
        weightsU.add(new ArrayList<>(Collections.nCopies(size, null)));

        return size;
    }

    private void _setEdge(int i, int j, W weight) {
        if  (i < j) {
            weightsU.get(i).set(j, weight);
        } else {
            weightsL.get(i).set(j, weight);
        }
    }

    @Override
    public void setEdge(int i, int j, W weight) {
        _checkSetEdgeArgs(i, j);

        _setEdge(i, j, weight);
    }

    @Override
    public void setEdge(T v1, T v2, W weight) {
        _checkSetEdgeArgs(v1, v2);

        int i = indexOfNode(v1);
        int j = indexOfNode(v2);

        _setEdge(i, j, weight);
    }
}
