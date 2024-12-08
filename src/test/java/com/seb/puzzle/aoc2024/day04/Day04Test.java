package com.seb.puzzle.aoc2024.day04;

import com.seb.core.Puzzle;
import com.seb.input.InputReader;
import junit.framework.TestCase;
import org.junit.Assert;

public class Day04Test extends TestCase {

    private static final InputReader inputReader = new InputReader();
    private Puzzle cut;
    private String input;

    @Override
    public void setUp() {
        cut = new Day04();
        input = inputReader.read(cut.inputFileName());
        cut.commonPart(input);
    }

    public void testSolvePart1() {
        Assert.assertEquals("2532", cut.solvePart1(input));
    }

    public void testSolvePart2() {
        Assert.assertEquals("1941", cut.solvePart2(input));
    }
}
