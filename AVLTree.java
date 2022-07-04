package com.example.projectds3;

public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

    private Node<T> rotateRight(Node<T> node) {
        Node<T> nodeLeft = node.getLeft();

        node.setLeft(nodeLeft.getRight());

        nodeLeft.setRight(node);

        return nodeLeft;
    }

    private Node<T> rotateLeft(Node<T> node){
        Node<T> nodeRight = node.getRight();

        node.setRight(nodeRight.getLeft());

        nodeRight.setLeft(node);

        return nodeRight;
    }

    private Node<T> rightLeftRotation(Node<T> node){
        Node<T> nodeRight = node.getRight();

        node.setRight(rotateRight(nodeRight));

        return rotateLeft(node);
    }

    private Node<T> leftRightRotation(Node<T> node){
        Node<T> nodeLeft = node.getLeft();

        node.setLeft(rotateLeft(nodeLeft));

        return rotateRight(node);
    }

    @Override
    public void insert(T data) {
        if(isEmpty()) {
            root = new Node<>(data);
        } else {
            Node<T> rootNode = root;

            addEntry(data, rootNode);

            root = Rebalance(rootNode);
        }
    }

    @Override
    public Node<T> delete(T data) {
        Node<T> temp = super.delete( data );

        if(temp != null) {
            Node<T> rootNode = root;

            root = Rebalance(rootNode);
        }

        return temp;
    }

    private void addEntry(T data, Node<T> rootNode) {
        assert rootNode != null;

        if(data.compareTo(rootNode.getData()) < 0) {
            if(rootNode.hasLeft()) {
                Node<T> leftChild = rootNode.getLeft();

                addEntry(data, leftChild);

                rootNode.setLeft( Rebalance(leftChild));
            } else {
                rootNode.setLeft(new Node<T>(data));
            }
        } else {
            if(rootNode.hasRight()) {
                Node<T> rightChild = rootNode.getRight();

                addEntry(data, rightChild);

                rootNode.setRight( Rebalance(rightChild));
            } else {
                rootNode.setRight(new Node<T>(data));
            }
        }
    }

    private Node<T> Rebalance ( Node<T> node){
        int heightDiff = getHeightDiff(node);

        if(heightDiff > 1) {
            if(getHeightDiff(node.getLeft()) > 0) {
                node = rotateRight(node);
            } else {
                node = leftRightRotation(node);
            }
        } else if (heightDiff < -1) {
            if(getHeightDiff(node.getRight()) < 0) {
                node = rotateLeft(node);
            } else {
                node = rightLeftRotation(node);
            }
        }

        return node;
    }

    private int getHeightDiff(Node<T>node){
        if(node != null)
            return getHeight(node.getLeft()) - getHeight(node.getRight());
        return 0;
    }


}
