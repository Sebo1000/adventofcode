package com.seb.puzzle.aoc2024.day02;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import org.apache.commons.lang3.NotImplementedException;

public class Day02 extends AbstractPuzzle {

    public static void main(String[] args) {
        PuzzleRunner.run(new Day02());
    }

    private String[] lines;

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.ALL;
    }

    public void commonPart(String rawInput) {
        lines = splitInputLines(rawInput);
    }

    @Override
    public String solvePart1(String rawInput) {
        throw new NotImplementedException("Solve me !");
    }

    @Override
    public String solvePart2(String rawInput) {
        throw new NotImplementedException("Solve me !");
    }

}
