package ru.geekbrains.task;

import ru.geekbrains.structure.TwoDirectionalSymmetricGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShortestDistance {
    public static <T> Float Dijkstra(TwoDirectionalSymmetricGraph<T, Float> graph, T from, T to) {
        Float[] shortestDistance = new Float[graph.getNodes().size()];

        // Init unvisitedIndices
        List<Integer> unvisitedIndices = new ArrayList<>();
        for (int i = 0; i < graph.getNodes().size(); i++) {
            unvisitedIndices.add(i);
        }

        // Init currentIndex
        int currentIndex = graph.indexOfNode(from);
        shortestDistance[currentIndex] = 0f;

        while (true) {
            Float currentDistance = shortestDistance[currentIndex];

            for (int j: graph.getNeighbours(currentIndex)) {
                if (unvisitedIndices.contains(j)) {
                    float newDistance = currentDistance + graph.getEdge(currentIndex, j);
                    if (
                            shortestDistance[j] == null ||
                            shortestDistance[j] > newDistance
                    ) {
                        shortestDistance[j] = newDistance;
                    }
                }
            }

            unvisitedIndices.remove((Integer) currentIndex);

            if (unvisitedIndices.size() == 0) {
                // All nodes are visited
                break;
            }

            Float minDistance = null;
            for (int i: unvisitedIndices) {
                Float distance = shortestDistance[i];
                if (minDistance == null || (distance != null && minDistance > distance)) {
                    minDistance = distance;
                    currentIndex = i;
                }
            }

            if (minDistance == null) {
                // There is unreachable nodes
                break;
            }
        }

        return shortestDistance[graph.indexOfNode(to)];
    }
}
