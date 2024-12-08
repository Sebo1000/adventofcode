package com.seb.puzzle.aoc2024.day03;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Day03 extends AbstractPuzzle {

    private String[] lines;

    public static void main(String[] args) {
        PuzzleRunner.run(new Day03());
    }

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.SECOND_PART;
    }

    public void commonPart(String rawInput) {
        lines = splitInputLines(rawInput);
    }

    @Override
    public String solvePart1(String rawInput) {

        final String regex = "mul[(](?<a>[0-9]{0,3}),(?<b>[0-9]{0,3})[)]";
        final Pattern pattern = Pattern.compile(regex);

        int res = 0;
        int nbOccur = 0;
        for (String line : lines) {
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                log.debug(matcher.group());
                res += Integer.valueOf(matcher.group("a")) * Integer.valueOf(matcher.group("b"));
                nbOccur++;
            }
        }

        log.info("Nb occur : {}", nbOccur);
        return String.valueOf(res);
    }

    @Override
    public String solvePart2(String rawInput) {
        final String regex = "mul[(](?<a>[0-9]{0,3}),(?<b>[0-9]{0,3})[)]";
        final Pattern pattern = Pattern.compile(regex);

        int res = 0;
        int nbOccur = 0;

        String memory = lines[0];
        memory = memory.replaceAll("don't", "pon't");

        final Matcher matcher = pattern.matcher(memory);
        while (matcher.find()) {

            int mulIndex = memory.indexOf(matcher.group());

            int enableIndex = memory.indexOf("do");
            int disableIndex = memory.indexOf("pon't");

            while ((memory.indexOf("do", enableIndex + 1) < mulIndex) && (memory.indexOf("do", enableIndex + 1) > -1)) {
                enableIndex = memory.indexOf("do", enableIndex + 1);
            }
            while ((memory.indexOf("pon't", disableIndex + 1) < mulIndex) && (memory.indexOf("pon't", disableIndex + 1) > -1)) {
                disableIndex = memory.indexOf("pon't", disableIndex + 1);
            }

            boolean enableMode = (disableIndex > mulIndex) || ((mulIndex - enableIndex < mulIndex - disableIndex) && (disableIndex < mulIndex && enableIndex < mulIndex));

                log.info("mode : {}, mulIndex : {}", enableMode ? "enabled" : "disabled", mulIndex);
                log.debug("enableIndex " + enableIndex);
                log.debug("disableIndex " + disableIndex);

            if (enableMode) {
                //Enable
                res += Integer.valueOf(matcher.group("a")) * Integer.valueOf(matcher.group("b"));
                nbOccur++;
            }
        }

        log.info("Nb occur : {}", nbOccur);
        return String.valueOf(res);
    }
}
