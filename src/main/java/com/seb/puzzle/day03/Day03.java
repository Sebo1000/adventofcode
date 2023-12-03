package com.seb.puzzle.day03;

import com.seb.common.grid.Grid;
import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import com.seb.puzzle.day03.model.Symbol;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Character.isDigit;

@Slf4j
public class Day03 extends AbstractPuzzle {

    public static void main(String[] args) {
        PuzzleRunner.run(new Day03());
    }

    private final List<Integer> connectedNumbers = new ArrayList<>();
    Grid<Symbol> grid;


    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.SECOND_PART;
    }

    public Integer convertToInteger(List<Symbol> points) {
        StringBuilder res = new StringBuilder();
        for (Symbol point : points) {
            res.append(point.symbol);
        }
        if (!res.isEmpty()) {
            return Integer.valueOf(res.toString());
        }
        return null;
    }

    public boolean isConnectedToSymbol(Grid<Symbol> grid, List<Symbol> points) {
        for (Symbol point : points) {
            List<Symbol> allNeighbours = grid.getAllNeighbours(point);
            if (allNeighbours.stream().anyMatch(symbol -> symbol.symbol != '.' && !isDigit(symbol.symbol))) {
                return true;
            }
        }
        return false;
    }

    public boolean isConnectedToGear(Grid<Symbol> grid, List<Symbol> points) {
        for (Symbol point : points) {
            List<Symbol> allNeighbours = grid.getAllNeighbours(point);
            if (allNeighbours.stream().anyMatch(symbol -> symbol.symbol == '*')) {
                return true;
            }
        }
        return false;
    }

    public boolean isGear(Grid<Symbol> grid, Symbol point) {
        List<Symbol> allNeighbours = grid.getAllNeighbours(point);
        return allNeighbours.stream().filter(symbol -> isDigit(symbol.symbol)).count() >= 2;
    }

    @Override
    public void commonPart(String rawInput) {
        grid = new Grid<>(splitInputLines(rawInput), Symbol::new);

        List<Symbol> currentNumber = new ArrayList<>();
        for (Symbol point : grid) {
            if (isDigit(point.symbol)) {
                currentNumber.add(point);
            }
            if (point.symbol == '.' || (isDigit(point.symbol) && point.getX() == grid.columns() - 1)) {
                //C'est un point
                Symbol previous = grid.pointAt(point.getX() - 1, point.getY()).orElse(null);

                if (previous != null && isConnectedToSymbol(grid, currentNumber)) {
                    if (!currentNumber.toString().isEmpty()) {
                        connectedNumbers.add(convertToInteger(currentNumber));
                    }
                }
                currentNumber = new ArrayList<>();
            } else if (!isDigit(point.symbol)) {
                if (!currentNumber.toString().isEmpty()) {
                    connectedNumbers.add(convertToInteger(currentNumber));
                    currentNumber = new ArrayList<>();
                }
            }
        }
    }

    @Override
    public String solvePart1(String rawInput) {
        List<Integer> integerStream = connectedNumbers.stream().filter(Objects::nonNull).toList();
        log.info("nb " + integerStream.size());
        return String.valueOf(integerStream.stream().reduce(0, Integer::sum));
    }

    @Override
    public String solvePart2(String rawInput) {
        throw new NotImplementedException("Solve me !");
    }

}

