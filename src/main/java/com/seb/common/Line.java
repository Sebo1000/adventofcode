package com.seb.common;

import com.google.common.collect.AbstractIterator;
import com.seb.common.grid.Point;
import lombok.RequiredArgsConstructor;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

@RequiredArgsConstructor
public class Line extends AbstractCollection<Point> implements Collection<Point> {

    private final Point from;
    private final Point to;


    public boolean isHorizontalOrVertical() {
        return from.getX() == to.getX() || from.getY() == to.getY();
    }

    @Override
    public Iterator<Point> iterator() {
        return new AbstractIterator<>() {
            private final Point increment = new Point(
                    (int) Math.signum(to.getX() - from.getX()),
                    (int) Math.signum(to.getY() - from.getY()));

            private Point last;

            @Override
            protected Point computeNext() {
                if (last == null) {
                    last = from;
                } else if (last.equals(to)) {
                    endOfData();
                    last = null;
                } else {
                    last = last.plus(increment);
                }
                return last;
            }
        };
    }

    @Override
    public int size() {
        if (isHorizontalOrVertical()) {
            return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()) + 1;
        }
        return greatestCommonDivisor(Math.abs(from.getX() - to.getX()), Math.abs(from.getY() - to.getY())) + 1;
    }

    private int greatestCommonDivisor(int a, int b) {
        return b == 0 ? a : greatestCommonDivisor(b, a % b);
    }
}
