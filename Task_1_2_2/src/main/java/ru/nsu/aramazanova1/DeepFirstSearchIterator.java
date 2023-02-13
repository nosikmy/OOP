package ru.nsu.aramazanova1;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Deep First Search Iterator realization.
 */
public class DeepFirstSearchIterator<T> implements Iterator<T> {
    private final int modCount;
    Stack<Node<T>> stack;

    /**
     * constructor for iteration by DFS.
     *
     * @param root root of the tree
     */
    public DeepFirstSearchIterator(Node<T> root) {
        this.modCount = Node.getModCount();
        this.stack = new Stack<>();
        stack.add(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        Node<T> node = stack.pop();
        assert node != null;
        if (modCount != Node.getModCount()) {
            throw new ConcurrentModificationException("The tree was changed during iteration");
        }
        node.getChildren().forEach(x -> stack.push(x));
        return node.getValue();
    }
}
