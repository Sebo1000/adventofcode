package com.seb.puzzle.day03.model;

import com.seb.common.grid.Point;

public class Symbol extends Point {

    public char symbol;

    public Symbol(int x, int y, char symbol) {
        super(x, y);
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol='" + symbol + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
