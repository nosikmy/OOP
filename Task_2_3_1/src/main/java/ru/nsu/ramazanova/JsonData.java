package ru.nsu.ramazanova;

import com.google.gson.Gson;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

/**
 * Class for getting data of pizzeria workers from json file.
 */
public class JsonData {
    private final int columns;
    private final int rows;
    private final int foodCount;
    private final int squareSize;
    private final List<Point> walls;

    public JsonData(String file) {
        Gson gson = new Gson();
        JsonData jsonData;
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(
                Main.class.getClassLoader().getResourceAsStream(file)))) {
            jsonData = gson.fromJson(reader, JsonData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.columns = jsonData.columns;
        this.rows = jsonData.rows;
        this.foodCount = jsonData.rows;
        this.squareSize = jsonData.squareSize;
        this.walls = jsonData.walls;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getSquareSize() {
        return squareSize;
    }

    public List<Point> getWalls() {
        return walls;
    }
}