package com.seb.puzzle.day02;

import com.seb.input.InputReader;
import junit.framework.TestCase;
import org.junit.Assert;

public class Day02Test extends TestCase {

    private static final InputReader inputReader = new InputReader();
    private Day02 cut;
    private String input;

    @Override
    public void setUp() {
        cut = new Day02();
        input = inputReader.read(cut.inputFileName());
        cut.commonPart(input);
    }

    public void testSolvePart1() {
        Assert.assertEquals("2156", cut.solvePart1(input));
    }

    public void testSolvePart2() {
        Assert.assertEquals("66909", cut.solvePart2(input));
    }
}
