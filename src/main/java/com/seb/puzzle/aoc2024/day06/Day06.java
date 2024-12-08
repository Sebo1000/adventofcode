package com.seb.puzzle.aoc2024.day06;

import com.seb.common.grid.Grid;
import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import com.seb.puzzle.aoc2024.day06.model.Position;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class Day06 extends AbstractPuzzle {

    private String[] lines;


    private Grid<Position> grid;
    private Position currentPosition;


    public static void main(String[] args) {
        PuzzleRunner.run(new Day06());
    }

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.SECOND_PART;
    }

    public void commonPart(String rawInput) {
        lines = splitInputLines(rawInput);
        grid = generateGrid();
        log.info(grid.toString());
    }

    @Override
    public String solvePart1(String rawInput) {
        Position nextPosition = getNextPosition(currentPosition, grid, false);
        while (nextPosition != null) {
            if (nextPosition.getLetter().equals("#")) {
                turn(currentPosition, grid);
            } else {
                getNextPosition(currentPosition, grid, true);
            }
            nextPosition = getNextPosition(currentPosition, grid, false);
        }

        int res = 0;

        Iterator<Position> iterator = grid.iterator();
        while (iterator.hasNext()) {
            Position position = iterator.next();
            if (position.getLetter().equals("X"))
                res++;
        }
        return String.valueOf(res + 1);
    }

    @Override
    public String solvePart2(String rawInput) {
        int res = 0;

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines.length; j++) {
                Grid<Position> positions = generateGrid();

                Position position = positions.pointAt(i, j).get();
                if (!position.getLetter().equals("^")) {
                    position.setLetter("O");
                    positions.set(position);
                    boolean blocked = isBlocked(positions);
                    if (blocked) {
                        res++;
                    }
                }
            }
        }
        return String.valueOf(res);
    }

    private Grid<Position> generateGrid() {
        grid = new Grid<>(lines.length, lines.length);
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("");
            for (int j = 0; j < line.length; j++) {

                Position position = new Position(j, i, line[j]);
                grid.set(position);
                if (line[j].equals("^")) {
                    currentPosition = position;
                }
            }
        }
        return grid;
    }

    public Position getNextPosition(Position position, Grid<Position> grid, boolean move) {
        Position nextPosition;
        if (grid.pointAt(position.getX(), position.getY()).get().getLetter().equals("^")) {
            nextPosition = grid.pointAt(position.getX(), position.getY() - 1).orElse(null);
            if (move && nextPosition != null) {
                currentPosition = nextPosition;
                currentPosition.setLetter("^");
                grid.pointAt(position.getX(), position.getY()).get().setLetter("X");
            }
            return nextPosition;
        }

        if (grid.pointAt(position.getX(), position.getY()).get().getLetter().equals(">")) {
            nextPosition = grid.pointAt(position.getX() + 1, position.getY()).orElse(null);
            if (move && nextPosition != null) {
                currentPosition = nextPosition;
                currentPosition.setLetter(">");
                grid.pointAt(position.getX(), position.getY()).get().setLetter("X");
            }
            return nextPosition;
        }

        if (grid.pointAt(position.getX(), position.getY()).get().getLetter().equals("v")) {
            nextPosition = grid.pointAt(position.getX(), position.getY() + 1).orElse(null);
            if (move && nextPosition != null) {
                currentPosition = nextPosition;
                currentPosition.setLetter("v");
                grid.pointAt(position.getX(), position.getY()).get().setLetter("X");
            }
            return nextPosition;
        }

        if (grid.pointAt(position.getX(), position.getY()).get().getLetter().equals("<")) {
            nextPosition = grid.pointAt(position.getX() - 1, position.getY()).orElse(null);
            if (move && nextPosition != null) {
                currentPosition = nextPosition;
                currentPosition.setLetter("<");
                grid.pointAt(position.getX(), position.getY()).get().setLetter("X");
            }
            return nextPosition;
        }
        return null;
    }

    public void turn(Position currentPosition, Grid<Position> grid) {
        switch (currentPosition.getLetter()) {
            case "^" -> {
                currentPosition.setLetter(">");
                grid.pointAt(currentPosition.getX(), currentPosition.getY()).get().setLetter(">");
            }
            case ">" -> {
                currentPosition.setLetter("v");
                grid.pointAt(currentPosition.getX(), currentPosition.getY()).get().setLetter("v");
            }
            case "v" -> {
                currentPosition.setLetter("<");
                grid.pointAt(currentPosition.getX(), currentPosition.getY()).get().setLetter("<");
            }
            case "<" -> {
                currentPosition.setLetter("^");
                grid.pointAt(currentPosition.getX(), currentPosition.getY()).get().setLetter("^");
            }
        }
    }

    public boolean isBlocked(Grid<Position> newGrid) {
        List<Position> listOfObstacles = new ArrayList<>();
        Position nextPosition = getNextPosition(currentPosition, newGrid, false);

        while (nextPosition != null) {
            for (Position obstacle : listOfObstacles) {
                if (obstacle.getX() == nextPosition.getX() && obstacle.getY() == nextPosition.getY() && obstacle.getFromDirection().equals(currentPosition.getLetter())) {
                    return true;
                }
            }

            if (nextPosition.getLetter().equals("#") || nextPosition.getLetter().equals("O")) {
                listOfObstacles.add(new Position(nextPosition.getX(), nextPosition.getY(), "#", currentPosition.getLetter()));
                turn(currentPosition, newGrid);
            } else {
                getNextPosition(currentPosition, newGrid, true);
            }
            nextPosition = getNextPosition(currentPosition, newGrid, false);
        }
        return false;
    }
}
