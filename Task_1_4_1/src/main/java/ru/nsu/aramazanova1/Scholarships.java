package ru.nsu.aramazanova1;

/**
 * Enum class for scholarships.
 */
public enum Scholarships {
    INCREASED("Повышенная"),
    ONEFOUR("Повышенная, но есть одна четверка"),
    NORMAL("Обычная"),
    NOSCHOLARSHIP("Стипендии нет(");

    private final String title;

    Scholarships(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}