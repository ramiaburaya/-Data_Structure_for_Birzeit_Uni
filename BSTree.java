package com.example.projectds3;


public class BSTree<T extends Comparable<T>> {
    protected Node<T> root;

    public int getSize () { // A method to get the number of nodes in the tree
        if (root == null)
            return 0;

        return getSize( root );
    }

    private int getSize ( Node<T> node ) {
        if (node != null)
            return getSize( node.getLeft() ) + 1 + getSize( node.getRight() );

        return 0;
    }

    public int getHeight () { // A method to get the height of a tree
        if (root == null)
            return 0;

        return getHeight( root );
    }

    public int getHeight ( Node<T> node ) {
        if (node != null) {
            int leftNodes = getHeight( node.getLeft() ), rightNodes = getHeight( node.getRight() );

            if (rightNodes > leftNodes)
                return rightNodes + 1;

            return leftNodes + 1;
        }

        return 0;
    }

    public int countLeaves () { // A method to count the number of leaves
        if (root == null)
            return 0;
        return countLeaves( root );
    }

    private int countLeaves ( Node<T> node ) {
        if (node != null) {
            if (node.isLeaf())
                return 1;

            return countLeaves( node.getLeft() ) + countLeaves( node.getRight() );
        }

        return 0;
    }

    public Node<T> getMin () { // A method to get the minimum, note how it gets the least node to the left
        if (root == null)
            return null;

        return getMin( root );
    }

    private Node<T> getMin ( Node<T> node ) {
        if (node.hasLeft()) {
            return getMin( node.getLeft() );
        } else {
            return node;
        }
    }

    public Node<T> getMax () { // A method to get the maximum, note how it gets the least node to the right
        if (root == null)
            return null;

        return getMax( root );
    }

    private Node<T> getMax ( Node<T> node ) {
        if (node.hasRight()) {
            return getMax( node.getRight() );
        } else {
            return node;
        }
    }


    public boolean isFull () { // A method to check if a tree is full or not (all nodes are either a leaves or have two children)
        if (root == null)
            return true;

        return isFull( root );
    }

    private boolean isFull ( Node<T> node ) {
        if (node != null) {
            if (node.isLeaf())
                return true;
            else if (node.hasTwoChildren())
                return isFull( node.getLeft() ) && isFull( node.getRight() );
            else
                return false;
        }

        return true;
    }

    public boolean isBalanced () { // This method is to check if the tree is balanced or not
        if (root != null)
            return isBalanced( root );
        return true;
    }

    public boolean isBalanced ( Node<T> node ) {
        if (node != null) {
            if (Math.abs( getHeight( node.getLeft() ) - getHeight( node.getRight() ) ) <= 1)
                return isBalanced( node.getLeft() ) && isBalanced( node.getRight() );
            return false;
        }

        return true;
    }

    public boolean isEmpty () { // A method to check if the tree is empty
        return root == null;
    }

    public String traverseInorder () { // A method to print the tree in increasing order
        return traverseInorder( root );
    }

    private String traverseInorder ( Node<T> node ) {

        if (node != null) {
            return traverseInorder( node.getLeft() ) + "\n" + node + "\n" + traverseInorder( node.getRight() );
        }
        return "";
    }


    public Node<T> Search ( T data ) { // A method that searches for a node
        if (root == null)
            return null;

        return Search( root, data );
    }

    private Node<T> Search ( Node<T> node, T data ) {
        if (node != null) {

            int comp = data.compareTo( node.getData() );

            if (comp > 0)
                return Search( node.getRight(), data );
            else if (comp < 0)
                return Search( node.getLeft(), data );
            else
                return node;
        } else {
            return null;
        }
    }

    public void insert ( T data ) { // A method to insert a node
        if (root == null)
            root = new Node<>( data );
        else
            insert( root, data );
    }

    private void insert ( Node<T> node, T data ) {
        if (data.compareTo( node.getData() ) > 0) {
            if (node.hasRight()) {
                insert( node.getRight(), data );
            } else {
                node.setRight( new Node<>( data ) );
            }
        } else {
            if (node.hasLeft()) {
                insert( node.getLeft(), data );
            } else {
                node.setLeft( new Node<>( data ) );
            }
        }

    }


    public Node<T> delete ( T data ) {
        Node<T> parent = root, current = root;
        boolean isLeftChild = false;

        while (current != null && !current.getData().equals( data )) {
            parent = current;

            if (data.compareTo( current.getData() ) > 0) {
                current = current.getRight();
                isLeftChild = false;
            } else {
                current = current.getLeft();
                isLeftChild = true;
            }
        }

        if (current == null)
            return null;

        if (current.isLeaf()) {
            if (current == root) { // Very important
                root = null;
            } else {
                if (isLeftChild)
                    parent.setLeft( null );
                else if (parent != null) {
                    parent.setRight( null );
                }
            }
        } else if (current.hasOneChild()) {
            if (current.hasLeft()) {
                if (current == root)
                    root = root.getLeft();
                else if (isLeftChild)
                    parent.setLeft( current.getLeft() );
                else if (parent != null) {
                    parent.setRight( current.getLeft() );
                }
            } else {
                if (current == root)
                    root = root.getRight();
                else if (isLeftChild)
                    parent.setRight( current.getRight() );
                else if (parent != null) {
                    parent.setRight( current.getLeft() );
                }
            }
        } else {

            Node<T> successor = delete( getMax( current.getLeft() ).getData() );

            successor.setLeft( current.getLeft() );

            successor.setRight( current.getRight() );


            if (current == root)
                root = successor;
            else if (isLeftChild)
                parent.setLeft( successor );
            else if (parent != null) {
                parent.setRight( successor );
            }

        }

        return current;

    }


    private Node<T> getSuccessor ( Node<T> node ) {
        Node<T> parentOfSuccessor = node;
        Node<T> successor = node;
        Node<T> current = node.getRight();
        while (current != null) {
            parentOfSuccessor = successor;
            successor = current;
            current = current.getLeft();
        }
        if (successor != node.getRight()) { // fix successor connections
            parentOfSuccessor.setLeft( successor.getRight() );
            successor.setRight( node.getRight() );
        }
        return successor;
    }
}
