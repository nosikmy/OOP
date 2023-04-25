package ru.nsu.ramazanova;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Snake {
    private Point head;
    private List<Point> body = new ArrayList<>();
    private Color color;
    private int size = 3;

    public Snake(Point head, Color color) {
        this.head = head;
        this.body.add(head);
        this.body.add(new Point(head.x-1, head.y));
        this.body.add(new Point(head.x-2, head.y));
        this.color = color;
    }

    public Point getHead() {
        return head;
    }

    public void setHead(Point head) {
        this.head = head;
    }

    public List<Point> getBody() {
        return body;
    }

    public void setBody(List<Point> body) {
        this.body = body;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
