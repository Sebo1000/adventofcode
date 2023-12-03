package com.seb.common.grid;

@FunctionalInterface
public interface PointFactory<T extends Point> {

    T create(int x, int y, char value);
}
