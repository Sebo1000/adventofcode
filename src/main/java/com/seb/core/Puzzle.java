package com.seb.core;

public interface Puzzle {

    default boolean isSample() {
        return false;
    }

    default PuzzlePart runPart() {
        return PuzzlePart.ALL;
    }

    default void commonPart(String rawInput) {
    }

    String solvePart1(String rawInput);

    String solvePart2(String rawInput);

    default String inputFileName() {
        String[] packagesParts = getClass().getPackageName().split("\\.");
        final String day = packagesParts[3] + "/" + packagesParts[4];
        if (isSample()) {
            return day + "/sample.txt";
        }
        return day + "/input.txt";
    }
}
