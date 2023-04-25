package ru.nsu.ramazanova;


import javafx.scene.paint.Color;

import java.awt.Point;
import java.util.Objects;

public class Food {
    private Point location = new Point();
    private Color color;

    public Food(Point location, Color color) {
        this.location = location;
        this.color = color;
    }
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return location.equals(food.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
