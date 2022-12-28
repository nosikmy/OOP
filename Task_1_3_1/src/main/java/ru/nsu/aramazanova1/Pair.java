package ru.nsu.aramazanova1;

/**
 * pair of line and entry index.
 *
 * @param line line with substring
 * @param entryIndex index of the first substring character in the line
 */
public record Pair(Integer line, Integer entryIndex) {
}
