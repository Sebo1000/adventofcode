package com.seb.common.grid;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class MatrixFactory {

    public <T extends Point> Point[][] create(String[] lines, PointFactory<T> pointFactory) {
        if (ArrayUtils.isEmpty(lines) || StringUtils.isEmpty(lines[0])) {
            return new Point[0][0];
        }
        Point[][] grid = new Point[lines.length][lines[0].length()];

        for (int y = 0; y < lines.length; y++) {
            grid[y] = createRow(lines[y], pointFactory, y);
        }
        return grid;
    }

    private <T extends Point> Point[] createRow(String line, PointFactory<T> factory, int y) {
        Point[] row = new Point[line.length()];

        for (int x = 0; x < line.length(); x++) {
            row[x] = factory.create(x, y, line.charAt(x));
        }
        return row;
    }
}
