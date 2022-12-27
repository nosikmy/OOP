package ru.nsu.aramazanova1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Class to define vertex.
 */
public class Node<T> implements Iterable<T> {
    private static int modCount = 0;

    public static int getModCount() {
        return modCount;
    }

    private T value;
    private Node<T> parent;
    private List<Node<T>> children;
    private TypesOfSearch typeOfSearch;

    public Node(T value) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
        typeOfSearch = TypesOfSearch.BFS;
    }

    public void setTypeOfSearch(TypesOfSearch typeOfSearch) {
        this.typeOfSearch = typeOfSearch;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getParent() {
        return parent;
    }

    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Node<T>> children) {
        this.children = children;
    }

    /**
     * Method that add children to this node.
     *
     * @param value value for new child
     * @return added child
     */
    public Node<T> addChild(T value) {
        Node<T> child = new Node<>(value);
        children.add(child);
        child.parent = this;
        modCount++;
        return child;
    }

    /**
     * Method that remove children from this node.
     */
    public void removeNode() throws Exception {
        if(this.parent == null){
            throw new Exception("Can't remove root");
        }
        Node<T> parent = getParent();
        children.forEach(x -> x.parent = parent);
        parent.children.remove(this);
        parent.children.addAll(children);
        modCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Iterator<T> iterator() {
        if (typeOfSearch == TypesOfSearch.BFS) {
            return new BFSIterator<>(this);
        } else {
            return new DFSIterator<>(this);
        }
    }
}
