package com.seb.common.grid;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@RequiredArgsConstructor
public class Point {

    private static final Pattern POINT_REGEX = Pattern.compile("^(\\d+),(\\d+)$");

    protected final int x;
    protected final int y;

    public Point(String coordinates) {
        Matcher matcher = POINT_REGEX.matcher(coordinates);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid point value");
        }
        x = Integer.parseInt(matcher.group(1));
        y = Integer.parseInt(matcher.group(2));
    }

    public Point plus(Point point) {
        return new Point(x + point.getX(), y + point.getY());
    }

    public static boolean isPoint(String text) {
        return POINT_REGEX.matcher(text).matches();
    }
}
