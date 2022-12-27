package ru.nsu.aramazanova1;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Breath First Search Iterator realization.
 */
public class BreathFirstSearchIterator<T> implements Iterator<T> {
    private final int modCount;
    Queue<Node<T>> queue;

    /**
     * constructor for iteration by BFS.
     *
     * @param root root of the tree
     */
    public BreathFirstSearchIterator(Node<T> root) {
        this.modCount = Node.getModCount();
        this.queue = new LinkedList<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        Node<T> node = queue.poll();
        assert node != null;
        if (modCount != Node.getModCount()) {
            throw new ConcurrentModificationException("The tree was changed during iteration");
        }
        queue.addAll(node.getChildren());
        return node.getValue();
    }
}
