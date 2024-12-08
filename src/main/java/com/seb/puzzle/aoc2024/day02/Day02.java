package com.seb.puzzle.aoc2024.day02;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends AbstractPuzzle {

    private String[] lines;

    private List<List<Integer>> reports;

    public static void main(String[] args) {
        PuzzleRunner.run(new Day02());
    }

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
        reports = new ArrayList<>();

        for (String line : lines) {
            List<Integer> levels = new ArrayList<>();
            String[] split = line.split(" ");
            for (String s : split) {
                levels.add(Integer.valueOf(s));
            }
            reports.add(levels);
        }
    }

    @Override
    public String solvePart1(String rawInput) {
        int nbReportsSafe = 0;
        for (List<Integer> report : reports) {
            boolean isSafe = checkReport(report);
            if (isSafe) nbReportsSafe++;
        }
        return String.valueOf(nbReportsSafe);
    }

    @Override
    public String solvePart2(String rawInput) {
        int nbReportsSafe = 0;
        for (List<Integer> report : reports) {
            boolean isSafe = checkReport(report);
            if (!isSafe) {
                isSafe = tryAlteredReport(report);
            }

            if (isSafe) nbReportsSafe++;
        }
        return String.valueOf(nbReportsSafe);
    }

    public boolean tryAlteredReport(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> alteredReport = new ArrayList<>(report);
            alteredReport.remove(i);
            if (checkReport(alteredReport)) return true;
        }
        return false;
    }

    public boolean checkReport(List<Integer> report) {
        Integer lastLevel = report.getFirst();
        boolean isIncreasing = report.get(1) > report.get(0);
        for (int i = 1; i < report.size(); i++) {
            Integer level = report.get(i);
            if (isIncreasing && level < lastLevel) return false;
            if (!isIncreasing && level > lastLevel) return false;

            int diff = isIncreasing ? level - lastLevel : lastLevel - level;
            if (1 > diff || diff > 3)
                return false;

            lastLevel = level;
        }
        return true;
    }
}
