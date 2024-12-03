package com.seb.puzzle.aoc2023.day01;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Character.isDigit;

@Slf4j
public class Day01 extends AbstractPuzzle {

    public static void main(String[] args) {
        PuzzleRunner.run(new Day01());
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

    @Override
    public void commonPart(String rawInput) {
        lines = splitInputLines(rawInput);
    }

    @Override
    public String solvePart1(String rawInput) {
        return String.valueOf(getSum(lines));
    }

    @Override
    public String solvePart2(String rawInput) {
        String[] numericLines = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];

            //Common words
            line = line.replace("oneight", "18");
            line = line.replace("twone", "21");
            line = line.replace("threeight", "38");
            line = line.replace("fiveight", "58");
            line = line.replace("sevenine", "79");
            line = line.replace("eightwo", "82");
            line = line.replace("eighthree", "83");
            line = line.replace("nineight", "98");

            line = line.replace("one", "1");
            line = line.replace("two", "2");
            line = line.replace("three", "3");
            line = line.replace("four", "4");
            line = line.replace("five", "5");
            line = line.replace("six", "6");
            line = line.replace("seven", "7");
            line = line.replace("eight", "8");
            line = line.replace("nine", "9");
            numericLines[i] = line;
        }

        return String.valueOf(getSum(numericLines));
    }

    private int getSum(final String[] lines) {
        int res = 0;

        for (String line : lines) {
            Character first = null, last = null;
            String lineSum = "";
            for (char c : line.toCharArray()) {
                if (isDigit(c)) {
                    if (first == null) {
                        first = c;
                    } else {
                        last = c;
                    }
                }
            }
            if (first != null && last == null) {
                lineSum = first + String.valueOf(first);
                res += Integer.parseInt(lineSum);
            }
            if (first != null && last != null) {
                lineSum = first + String.valueOf(last);
                res += Integer.parseInt(lineSum);
            }
        }
        return res;
    }
}

