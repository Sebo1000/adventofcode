package com.seb.common.grid;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class IntPoint extends Point {

    protected int value;

    public IntPoint(int x, int y, char value) {
        this(x, y, Character.getNumericValue(value));
    }

    public IntPoint(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    public void increaseValue() {
        value++;
    }

    public boolean isHigherThan(IntPoint point) {
        return value > point.getValue();
    }

    public boolean isLowerThanAll(List<? extends IntPoint> points) {
        return points.stream().allMatch(point -> point.isHigherThan(this));
    }
}
