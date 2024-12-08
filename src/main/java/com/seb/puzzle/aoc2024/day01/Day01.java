package com.seb.puzzle.aoc2024.day01;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class Day01 extends AbstractPuzzle {

    private String[] lines;
    private List<Integer> initialLeftList;
    private List<Integer> initialRightList;

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

        initialLeftList = new ArrayList<>();
        initialRightList = new ArrayList<>();

        for (String line : lines) {
            String[] s = line.split("  ");
            initialLeftList.add(Integer.parseInt(s[0].trim()));
            initialRightList.add(Integer.parseInt(s[1].trim()));
        }

    }

    @Override
    public String solvePart1(String rawInput) {

        List<Integer> leftList = new ArrayList<>(initialLeftList);
        List<Integer> rightList = new ArrayList<>(initialRightList);

        Integer sum = 0;

        for (int i = 0; i < lines.length; i++) {
            Integer[] left = getMinNumberFromList(leftList);
            Integer[] right = getMinNumberFromList(rightList);

            Integer leftValue = left[1];
            Integer rightValue = right[1];
            leftList.remove(left[0].intValue());
            rightList.remove(right[0].intValue());
            if (rightValue > leftValue) {
                sum += rightValue - leftValue;
            } else {
                sum += leftValue - rightValue;
            }

            log.debug("current sum {}, left: {}, right {}", sum, leftValue, rightValue);
        }

        return String.valueOf(sum);
    }

    public Integer[] getMinNumberFromList(List<Integer> list) {
        Integer min = list.get(0);
        int index = 0;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
                index = i;
            }
        }

        return new Integer[]{index, min};
    }


    @Override
    public String solvePart2(String rawInput) {
        int res = 0;
        for (Integer leftValue : initialLeftList) {
            res += leftValue * countOccur(initialRightList, leftValue);
        }
        return String.valueOf(res);
    }

    public int countOccur(List<Integer> list, Integer needle) {
        int count = 0;
        for (Integer value : list) {
            if (Objects.equals(value, needle)) count++;
        }
        return count;
    }
}

