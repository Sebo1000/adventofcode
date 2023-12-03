package com.seb.puzzle.day01;

import com.seb.core.Puzzle;
import com.seb.input.InputReader;
import junit.framework.TestCase;
import org.junit.Assert;

public class Day01Test extends TestCase {

    private static final InputReader inputReader = new InputReader();
    private Puzzle cut;
    private String input;

    @Override
    public void setUp() {
        cut = new Day01();
        input = inputReader.read(cut.inputFileName());
        cut.commonPart(input);
    }

    public void testSolvePart1() {
        Assert.assertEquals("54940", cut.solvePart1(input));
    }

    public void testSolvePart2() {
        Assert.assertEquals("54208", cut.solvePart2(input));
    }
}
