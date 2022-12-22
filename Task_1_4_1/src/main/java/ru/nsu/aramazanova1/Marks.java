package ru.nsu.aramazanova1;

import java.util.Arrays;

/**
 * Enum class for mark iteration.
 */
public enum Marks {
    FIVE("отл."),
    FOUR("хор."),
    THREE("удовл."),
    OFFSET("зачет");

    private final String title;

    Marks(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Integer getValue() {
        return switch (this) {
            case FIVE -> 5;
            case FOUR -> 4;
            case THREE -> 3;
            case OFFSET -> 0;
            default -> throw new IllegalArgumentException("Неправильное название оценки");
        };
    }

    /**
     * Function to find value.
     *
     * @param mark title
     * @return value
     */
    public static Marks getMark(String mark) {
        return Arrays.stream(values())
                .filter(value -> value.getTitle().equals(mark)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Неправильное название оценки"));
    }
}