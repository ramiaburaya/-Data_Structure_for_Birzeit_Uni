package com.example.projectds3;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> right;
    private Node<T> left;

    public Node(T data) {
        this.data = data;
    }

    public Node () {

    }

    @Override
    public String toString() {
        return data.toString();
    }

//    @Override
//    public int compareTo(Node<T> node) {
//        return data.compareTo(node.getData());
//    }

    public boolean isLeaf() {
        return (right == null && left == null);
    }

    public boolean hasTwoChildren() {
        return (hasLeft() && hasRight());
    }

    public boolean hasOneChild() {
        return (hasLeft() && !hasRight() || !hasLeft() && hasRight());
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T>  getRight() {
        return right;
    }

    public void setRight(Node<T>  right) {
        this.right = right;
    }

    public Node<T>  getLeft() {
        return left;
    }

    public void setLeft(Node<T>  left) {
        this.left = left;
    }
}
