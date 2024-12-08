package com.seb.puzzle.aoc2024.day06.model;

import com.seb.common.grid.Point;

public class Position extends Point {

    private String letter;
    private String fromDirection;

    public Position(int x, int y, String letter) {
        super(x, y);
        this.letter = letter;
    }

    public Position(int x, int y, String letter, String fromDirection) {
        super(x, y);
        this.letter = letter;
        this.fromDirection = fromDirection;
    }

    public String getFromDirection() {
        return fromDirection;
    }

    public void setFromDirection(String fromDirection) {
        this.fromDirection = fromDirection;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return letter;
    }
}
