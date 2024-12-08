package com.seb.puzzle.day;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import org.apache.commons.lang3.NotImplementedException;

public class Day extends AbstractPuzzle {

    private String[] lines;

    public static void main(String[] args) {
        PuzzleRunner.run(new Day());
    }

    @Override
    public boolean isSample() {
        return true;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.FIRST_PART;
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
