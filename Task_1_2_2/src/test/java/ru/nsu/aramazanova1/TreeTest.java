package ru.nsu.aramazanova1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

/**
 * Tests for Tree realisation.
 */
public class TreeTest {
    @Test
    public void testBFS() throws Exception {
        Node<Integer> root = new Node<>(1);
        root.addChild(2);
        root.addChild(3);
        Node<Integer> child4 = root.addChild(4);
        child4.addChild(5);
        child4.addChild(6);
        Node<Integer> child7 = root.addChild(7);
        child7.addChild(8);
        child7.addChild(9);
        child7.addChild(10);
        child7.removeNode();
        root.setTypeOfSearch(TypesOfSearch.BFS);
        Integer actual = 0;
        Iterator<Integer> iterator = root.iterator();
        while (iterator.hasNext()){
            actual++;
            iterator.next();
        }
        Assertions.assertEquals(9, actual);
    }

    @Test
    public void testDFS() throws Exception {
        Node<Integer> root = new Node<>(1);
        root.addChild(2);
        root.addChild(3);
        Node<Integer> child4 = root.addChild(4);
        child4.addChild(5);
        child4.addChild(6);
        Node<Integer> child7 = root.addChild(7);
        child7.addChild(8);
        child7.addChild(9);
        child7.addChild(10);
        child7.removeNode();
        root.setTypeOfSearch(TypesOfSearch.DFS);
        Integer actual = 0;
        Iterator<Integer> iterator = root.iterator();
        while (iterator.hasNext()){
            actual++;
            iterator.next();
        }
        Assertions.assertEquals(9, actual);
    }
}
