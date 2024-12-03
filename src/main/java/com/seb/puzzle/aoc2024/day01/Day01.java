package com.seb.puzzle.aoc2024.day01;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

@Slf4j
public class Day01 extends AbstractPuzzle {

    private String[] lines;

    public static void main(String[] args) {
        PuzzleRunner.run(new Day01());
    }

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.ALL;
    }

    @Override
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

