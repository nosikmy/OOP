package ru.nsu.ramazanova;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    static JsonData jsonData = new JsonData("example.json");
    private static final int square = jsonData.getSquareSize();
    private static final int rows = jsonData.getRows();
    private static final int columns = jsonData.getColumns();
    private static final int height = rows * square;
    private static final int width = columns * square;


    private static final int menuWidth = 300;
    private static final int goal = (int) (Math.random() * 10 + 50);
    private static final Integer[] foodCounter = new Integer[]{0, 0, 0, 0};
    private static final Integer[] foodGoal = new Integer[]{
            (int) (Math.random() * 10 + 5),
            (int) (Math.random() * 10 + 5),
            (int) (Math.random() * 10 + 5),
            (int) (Math.random() * 10 + 5)
    };

    private final Snake snake = new Snake(new Point((int) (Math.random() * (columns - 2)) + 2,
            (int) (Math.random() * rows)), Color.web("67E300"));

    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    private Direction direction = Direction.RIGHT;
    private static Timeline timeline;
    private final int countOfFood = jsonData.getFoodCount();
    private final List<Food> food = new ArrayList<>();
    private final List<Color> colorsOfFood = List.of(
            Color.web("FF7A00"), Color.web("34D800"),
            Color.web("F30021"), Color.web("4512AE"));

    private final List<Point> walls = jsonData.getWalls();
    private Boolean win = false;
    private Boolean lose = false;
    GraphicsContext graphicsContext;
    GraphicsContext scoreGraphicContext;

    @Override
    public void start(Stage primaryStage) throws Exception {

        HBox root = new HBox();
        Canvas canvas = new Canvas(width, height);
        Canvas scoreCanvas = new Canvas(menuWidth, height);
        root.getChildren().addAll(canvas, scoreCanvas);
        graphicsContext = canvas.getGraphicsContext2D();
        scoreGraphicContext = scoreCanvas.getGraphicsContext2D();

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.RIGHT && direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            } else if (code == KeyCode.LEFT && direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            } else if (code == KeyCode.UP && direction != Direction.DOWN) {
                direction = Direction.UP;
            } else if (code == KeyCode.DOWN && direction != Direction.UP) {
                direction = Direction.DOWN;
            } else if (code == KeyCode.ESCAPE) {
                primaryStage.close();
            }
        });
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();


        timeline = new Timeline(new KeyFrame(Duration.millis(300), e -> startGame()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        for (int i = 0; i < countOfFood; i++) {
            food.add(new Food(generateFoodLocation(),
                    colorsOfFood.get((int) (Math.random() * 4))));
        }
    }

    private void startGame() {
        if (lose) {
            drawLose();
            return;
        } else if (win) {
            drawWin();
            return;
        }
        drawBackground();
        drawFood();
        drawSnake();
        drawWalls();
        drawScore();

        for (int i = snake.getSize() - 1; i >= 1; i--) {
            snake.getBody().get(i).setLocation(snake.getBody().get(i - 1));
        }

        switch (direction) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        gameOver();
        eatFood();
        timeline.setRate((double) snake.getSize()/10 + 1);
    }

    private void drawLose() {
        scoreGraphicContext.setFill(Color.RED);
        scoreGraphicContext.setFont(new Font("Digital-7", 30));
        scoreGraphicContext.fillText("Вы проиграли:(", 10, 300);
    }

    private void drawWin() {
        scoreGraphicContext.setFill(Color.GREEN);
        scoreGraphicContext.setFont(new Font(20));
        scoreGraphicContext.fillText("Победа!!!", 10, 300);
    }

    private void drawWalls() {
        Image image = new Image("wall.png");
        for (Point wall : walls) {
            graphicsContext.drawImage(image, wall.x * square,
                    wall.y * square, square, square);
        }
    }


    private void gameOver() {
        if (walls.stream()
                .anyMatch(x -> x.x == snake.getHead().x && x.y == snake.getHead().y)) {
            lose = true;
        }

        if (goal <= snake.getSize()
                && foodGoal[0] <= foodCounter[0]
                && foodGoal[1] <= foodCounter[1]
                && foodGoal[2] <= foodCounter[2]
                && foodGoal[3] <= foodCounter[3]) {
            win = true;
        }

        for (int i = 1; i < snake.getSize(); i++) {
            if (snake.getHead().x == snake.getBody().get(i).getX()
                    && snake.getHead().getY() == snake.getBody().get(i).getY()) {
                snake.setSize(i);
                break;
            }
        }

    }

    private void moveDown() {
        snake.getHead().y++;
        if (snake.getHead().y >= height / square) {
            snake.getHead().y = 0;
        }
    }

    private void moveUp() {
        snake.getHead().y--;
        if (snake.getHead().y < 0) {
            snake.getHead().y = height / square;
        }
    }

    private void moveLeft() {
        snake.getHead().x--;
        if (snake.getHead().x < 0) {
            snake.getHead().x = width / square;
        }
    }


    private void moveRight() {
        snake.getHead().x++;
        if (snake.getHead().x >= width / square) {
            snake.getHead().x = 0;
        }
    }


    private void drawBackground() {
        graphicsContext.setFill(Color.web("CCF600"));
        graphicsContext.fillRect(0, 0, width, height);
        graphicsContext.setFill(Color.web("FFFFFF"));
        for (int i = 0; i <= columns; i++) {
            graphicsContext.fillRect(i * square, 0, 1, height);
        }
        for (int i = 0; i <= rows; i++) {
            graphicsContext.fillRect(0, i * square, width, 1);
        }
        graphicsContext.fillRect(width, 0, menuWidth, height);

    }

    private void drawFood() {
        for (int i = 0; i < countOfFood; i++) {
            graphicsContext.setFill(food.get(i).getColor());
            graphicsContext.fillOval(food.get(i).getLocation().x * square + 5, food.get(i).getLocation().y * square + 5, square - 10, square - 10);
        }
    }

    private void drawSnake() {
        Image image = new Image("headRight.png");
        switch (direction) {
            case RIGHT -> image = new Image("headRight.png");
            case LEFT -> image = new Image("headLeft.png");
            case UP -> image = new Image("headUp.png");
            case DOWN -> image = new Image("headDown.png");
        }
        graphicsContext.setFill(snake.getColor());

        graphicsContext.drawImage(image, snake.getHead().x * square,
                snake.getHead().y * square, square, square);
        for (int i = 1; i < snake.getSize(); i++) {
            graphicsContext.fillRoundRect(snake.getBody().get(i).x * square,
                    snake.getBody().get(i).y * square + 3,
                    square, square - 6, 5, 10);
        }
    }

    private void drawScore() {
        scoreGraphicContext.setFill(Color.WHITE);
        scoreGraphicContext.fillRect(0, 0, menuWidth, height);
        scoreGraphicContext.setFill(Color.BLACK);
        scoreGraphicContext.setFont(new Font("Digital-7", 20));
        scoreGraphicContext.fillText("Вырасти: " + snake.getSize() + " из " + goal, 10, 30);
        Image image = new Image("checkMark.png");
        if (goal <= snake.getSize()) {
            scoreGraphicContext.drawImage(image, 170,
                    15, square, square);
        }
        scoreGraphicContext.fillText("Cъесть: ", 10, 60);
        for (int i = 0; i < foodGoal.length; i++) {
            scoreGraphicContext.setFill(colorsOfFood.get(i));
            scoreGraphicContext.fillOval(10, 77 + i * 30, square - 10, square - 10);
            scoreGraphicContext.setFill(Color.BLACK);
            scoreGraphicContext.fillText(foodCounter[i] + " из " + foodGoal[i], 30, 90 + i * 30);
            if (foodCounter[i] >= foodGoal[i]) {
                scoreGraphicContext.drawImage(image, 110,
                        75 + i * 30, square, square);
            }
        }
    }

    private void eatFood() {
        food.stream().filter(x -> x.getLocation().equals(snake.getHead()))
                .forEach(x -> {
                    x.setLocation(generateFoodLocation());
                    x.setColor(colorsOfFood.get((int) (Math.random() * 4)));
                    if (snake.getBody().size() == snake.getSize()) {
                        snake.getBody().add(new Point(-1, -1));
                    } else {
                        snake.getBody().set(snake.getSize(), new Point(-1, -1));
                    }
                    //snake.getBody().add(new Point(-1, -1));
                    snake.setSize(snake.getSize() + 1);
                    for (int i = 0; i < foodGoal.length; i++) {
                        if (x.getColor().equals(colorsOfFood.get(i))) {
                            foodCounter[i]++;
                        }
                    }
                });
    }

    private Point generateFoodLocation() {
        Point point = new Point((int) (Math.random() * columns), (int) (Math.random() * rows));
        while (food.stream()
                .anyMatch(f ->
                        f.getLocation().x == point.x &&
                                f.getLocation().y == point.y &&
                                walls.stream().anyMatch(w ->
                                        w.x == f.getLocation().x && w.y == f.getLocation().y))) {
            point.setLocation((int) (Math.random() * columns), (int) (Math.random() * rows));
        }
        return point;
    }
}