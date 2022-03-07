package ru.geekbrains.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTree<T extends Comparable<T>> {
    private static class Node<T extends Comparable<T>> {
        T value;
        Node<T> left = null;
        Node<T> right = null;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> start = null;

    private static <T extends Comparable<T>> Node<T> fillBalanced(List<T> list) {
        if (list.size() == 0) {
            return null;
        }

        int middle = list.size() / 2;

        Node<T> node = new Node<>(list.get(middle));

        if (list.size() == 1) {
            return node;
        }

        node.left = fillBalanced(list.subList(0, middle));
        node.right = fillBalanced(list.subList(middle + 1, list.size()));

        return node;
    }

    public static <T extends Comparable<T>> BinaryTree<T> buildBalancedTree(List<T> origItems) {
        List<T> cpyItems = new ArrayList<>(origItems);
        Collections.sort(cpyItems);

        Node<T> start = fillBalanced(cpyItems);

        BinaryTree<T> tree = new BinaryTree<>();
        tree.start = start;

        return tree;
    }

    private void add(Node<T> node, T item) {
        if (item.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node<>(item);
            } else {
                add(node.left, item);
            }
        } else {
            if (node.right == null) {
                node.right = new Node<>(item);
            } else {
                add(node.right, item);
            }
        }
    }

    public void add(T item) {
        if (start == null) {
            start = new Node<>(item);
            return;
        }
        add(start, item);
    }

    public static <T extends Comparable<T>> BinaryTree<T> buildTree(List<T> items) {
        BinaryTree<T> tree = new BinaryTree<>();

        for (T item: items) {
            tree.add(item);
        }

        return tree;
    }

    private int getMaxDepth(Node<T> node) {
        return node == null ? 0 : 1 + Math.max(getMaxDepth(node.left), getMaxDepth(node.right));
    }

    public int getMaxDepth() {
        return getMaxDepth(start);
    }

    private int getSize(Node<T> node) {
        return node == null ? 0 : 1 + getSize(node.left) + getSize(node.right);
    }

    public int getSize() {
        return getSize(start);
    }

    public boolean isBalanced() {
        return (int) (Math.log(getSize()) / Math.log(2)) + 1 == getMaxDepth();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Node<T>> prevLayer, nextLayer = new ArrayList<>();

        if (start != null) {
            nextLayer.add(start);
        }

        while(nextLayer.size() != 0) {
            prevLayer = nextLayer;
            nextLayer = new ArrayList<>();

            for (Node<T> node: prevLayer) {
                sb.append(node.value).append(" ");
                if (node.left != null) {
                    nextLayer.add(node.left);
                }
                if (node.right != null) {
                    nextLayer.add(node.right);
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
