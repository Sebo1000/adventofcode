package com.seb.puzzle.aoc2024.day04;

import com.seb.common.grid.Grid;
import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import com.seb.puzzle.aoc2024.day04.model.Letter;
import com.seb.puzzle.aoc2024.day04.model.Sens;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.seb.puzzle.aoc2024.day04.model.Sens.*;

@Slf4j
public class Day04 extends AbstractPuzzle {

    public List<Letter> commonsX;
    public int nbCommons = 0;
    private String[] lines;
    private Grid<Letter> grid;

    public static void main(String[] args) {
        PuzzleRunner.run(new Day04());
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
        commonsX = new ArrayList<>();

        grid = new Grid<>(lines.length, lines.length);
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split("");
            for (int j = 0; j < line.length; j++) {
                grid.set(new Letter(j, i, line[j]));
            }
        }
    }

    @Override
    public String solvePart1(String rawInput) {

        int nbWord = 0;

        String[] xmas = new String[]{"X", "M", "A", "S"};

        nbWord += getNbWord(W_TO_E, xmas);
        nbWord += getNbWord(NW_TO_SE, xmas);
        nbWord += getNbWord(N_TO_S, xmas);
        nbWord += getNbWord(NE_TO_SW, xmas);
        nbWord += getNbWord(E_TO_W, xmas);
        nbWord += getNbWord(SE_TO_NW, xmas);
        nbWord += getNbWord(S_TO_N, xmas);
        nbWord += getNbWord(SW_TO_NE, xmas);

        return String.valueOf(nbWord);
    }

    @Override
    public String solvePart2(String rawInput) {

        String[] mas = new String[]{"M", "A", "S"};
        getNbWord(NW_TO_SE, mas);
        getNbWord(NE_TO_SW, mas);
        getNbWord(SE_TO_NW, mas);
        getNbWord(SW_TO_NE, mas);

        return String.valueOf(nbCommons);
    }

    private int getNbWord(Sens sens, String[] needle) {
        Iterator<Letter> iterator = grid.iterator();

        Letter potentialCommon = null;

        int nbWord = 0;
        while (iterator.hasNext()) {
            Letter letter = iterator.next();
            int index = 1;
            boolean continuer = true;

            Letter currentLetter = letter;
            if (currentLetter.getLetter().equals(needle[0])) {
                do {
                    Optional<Letter> nextLetter = getNextLetter(currentLetter, sens);
                    if (nextLetter.isPresent() || index == needle.length) {
                        if (index == needle.length) {
                            log.info("Found word {} ({}, {})", sens, letter.getX(), letter.getY());
                            if (commonsX.contains(potentialCommon)) {
                                nbCommons++;
                            } else {
                                commonsX.add(potentialCommon);
                            }
                            nbWord++;
                            continuer = false;
                            index = 1;
                        } else if (nextLetter.get().getLetter().equals(needle[index])) {
                            if (needle[index].equals("A")) {
                                potentialCommon = nextLetter.get();
                            }
                            currentLetter = nextLetter.get();
                            index++;
                        } else {
                            continuer = false;
                        }
                    } else {
                        continuer = false;
                    }
                } while (continuer);
            }

        }
        return nbWord;
    }

    private Optional<Letter> getNextLetter(Letter currentLetter, Sens sens) {
        switch (sens) {
            case W_TO_E:
                return grid.pointAt(currentLetter.getX() + 1, currentLetter.getY());
            case NW_TO_SE:
                return grid.pointAt(currentLetter.getX() + 1, currentLetter.getY() + 1);
            case N_TO_S:
                return grid.pointAt(currentLetter.getX(), currentLetter.getY() + 1);
            case NE_TO_SW:
                return grid.pointAt(currentLetter.getX() - 1, currentLetter.getY() + 1);
            case E_TO_W:
                return grid.pointAt(currentLetter.getX() - 1, currentLetter.getY());
            case SE_TO_NW:
                return grid.pointAt(currentLetter.getX() - 1, currentLetter.getY() - 1);
            case S_TO_N:
                return grid.pointAt(currentLetter.getX(), currentLetter.getY() - 1);
            case SW_TO_NE:
                return grid.pointAt(currentLetter.getX() + 1, currentLetter.getY() - 1);
            default:
                throw new IllegalArgumentException();
        }
    }


}
