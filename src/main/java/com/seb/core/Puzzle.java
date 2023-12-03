package com.seb.core;

public interface Puzzle {

    default boolean isSample(){
        return false;
    }

    default PuzzlePart runPart(){
        return PuzzlePart.ALL;
    }

    default void commonPart(String rawInput){}

    String solvePart1(String rawInput);

    String solvePart2(String rawInput);

    default String inputFileName() {
        if (isSample()){
            return getClass().getSimpleName().toLowerCase() + "/" + "sample.txt";
        }
        return getClass().getSimpleName().toLowerCase() + "/" + "input.txt";
    }
}
