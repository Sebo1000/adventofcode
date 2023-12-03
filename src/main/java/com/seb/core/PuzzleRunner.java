package com.seb.core;

import com.seb.input.InputReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PuzzleRunner {

    private static final InputReader inputReader = new InputReader();

    public static void run(Puzzle puzzle) {
        log.info("Solving puzzle for " + puzzle.getClass().getSimpleName() + ":");

        String input = inputReader.read(puzzle.inputFileName());

        puzzle.commonPart(input);

        if (puzzle.runPart() == PuzzlePart.FIRST_PART || puzzle.runPart() == PuzzlePart.ALL) {
            log.info("Solution for part 1: " + puzzle.solvePart1(input));
        }

        if (puzzle.runPart() == PuzzlePart.SECOND_PART || puzzle.runPart() == PuzzlePart.ALL) {
            log.info("Solution for part 2: " + puzzle.solvePart2(input));
        }

    }
}
