package com.seb.puzzle.aoc2024.day02;

import com.seb.core.Puzzle;
import com.seb.input.InputReader;
import junit.framework.TestCase;
import org.junit.Assert;

public class Day02Test extends TestCase {

    private static final InputReader inputReader = new InputReader();
    private Puzzle cut;
    private String input;

    @Override
    public void setUp() {
        cut = new Day02();
        input = inputReader.read(cut.inputFileName());
        cut.commonPart(input);
    }

    public void testSolvePart1() {
        Assert.assertEquals("279", cut.solvePart1(input));
    }

    public void testSolvePart2() {
        Assert.assertEquals("343", cut.solvePart2(input));
    }
}
