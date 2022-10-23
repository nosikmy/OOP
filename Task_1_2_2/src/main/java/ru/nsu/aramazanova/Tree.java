package ru.nsu.aramazanova;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {
    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    private final T value;
    private final List<Tree<T>> children;
    private Tree<T> parent;

    public Tree(T val){
        value = val;
        children = new ArrayList<>();
        parent = null;
    }

    public void addNode(T value){
        Tree<T> child = new Tree<>(value);
        child.parent = this;
        children.add(child);
    }

    public T removeNode(){
        parent.children.remove(this);
        children.forEach(child -> child.setParent(parent));
        parent.children.addAll(children);
        return value;
    }
}
