package com.seb.common.grid;

import com.google.common.collect.AbstractIterator;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Grid<T extends Point> extends AbstractCollection<T> implements Collection<T> {

    private static final MatrixFactory MATRIX_FACTORY = new MatrixFactory();

    private final Point[][] grid;

    public Grid(int rows, int columns) {
        this.grid = new Point[columns][rows];
    }

    public Grid(String[] lines, PointFactory<T> pointFactory) {
        this.grid = MATRIX_FACTORY.create(lines, pointFactory);
    }

    public List<T> getAllNeighbours(T point) {
        return Stream.of(getDiagonalNeighbours(point), getHorizontalAndVerticalNeighbours(point))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<T> getDiagonalNeighbours(T point) {
        return getNeighbours(
                pointAt(point.getX() + 1, point.getY() - 1),
                pointAt(point.getX() + 1, point.getY() + 1),
                pointAt(point.getX() - 1, point.getY() - 1),
                pointAt(point.getX() - 1, point.getY() + 1));
    }

    public List<T> getHorizontalAndVerticalNeighbours(T point) {
        return getNeighbours(
                pointAt(point.getX() + 1, point.getY()),
                pointAt(point.getX() - 1, point.getY()),
                pointAt(point.getX(), point.getY() + 1),
                pointAt(point.getX(), point.getY() - 1));
    }

    public int size() {
        return rows() * columns();
    }

    public int rows() {
        return grid.length;
    }

    public int columns() {
        if (grid.length == 0) {
            return 0;
        }
        return grid[0].length;
    }

    public Optional<T> pointAt(int x, int y) {
        return isOutOfTheGrid(x, y) ? Optional.empty() : Optional.of(cast(grid[y][x]));
    }

    public void set(T point) {
        grid[point.getY()][point.getX()] = point;
    }

    @SafeVarargs
    private List<T> getNeighbours(Optional<T>... candidates) {
        return Arrays.stream(candidates).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
    }

    private boolean isOutOfTheGrid(int x, int y) {
        return y < 0 || y >= grid.length || x < 0 || x >= grid[y].length;
    }

    @Override
    public Iterator<T> iterator() {
        return new AbstractIterator<>() {
            int x, y;

            @Override
            protected T computeNext() {
                if (x > 0 && x >= grid[y].length) {
                    x = 0;
                    y++;
                }
                if (y >= grid.length) {
                    endOfData();
                    return null;
                }
                return cast(grid[y][x++]);
            }
        };
    }

    @SuppressWarnings("unchecked")
    private T cast(Point point) {
        return (T) point;
    }
}
