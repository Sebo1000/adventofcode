package com.seb.puzzle.aoc2024.day04.model;

import com.seb.common.grid.Point;

public class Letter extends Point {

    private String letter;

    public Letter(int x, int y, String letter) {
        super(x, y);
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    @Override
    public String toString() {
        return letter;
    }
}
