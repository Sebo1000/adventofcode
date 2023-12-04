package com.seb.puzzle.day04;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Day04 extends AbstractPuzzle {

    public static void main(String[] args) {
        PuzzleRunner.run(new Day04());
    }

    private String[] lines;
    private List<Card> cards = new ArrayList<>();

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public PuzzlePart runPart() {
        return PuzzlePart.SECOND_PART;
    }

    @Override
    public void commonPart(String rawInput) {
        lines = splitInputLines(rawInput);
        for (String line : lines) {
            Card card = new Card();
            String[] split = line.split(": ");
            String id = split[0].split("Card")[1].trim();
            card.id = Integer.valueOf(id);
            String[] allNumbers = split[1].split(" \\| ");
            String[] winningNumbers = allNumbers[0].split(" ");
            String[] numbers = allNumbers[1].split(" ");
            card.winningNumbers.addAll(Arrays.stream(winningNumbers).filter(r -> !r.isBlank()).map(Integer::valueOf).toList());
            card.numbers.addAll(Arrays.stream(numbers).filter(r -> !r.isBlank()).map(Integer::valueOf).toList());
            cards.add(card);
        }
        log.warn("nbCard {}", cards.size());
    }

    @Override
    public String solvePart1(String rawInput) {
        int res = 0;
        for (Card card : cards) {
            int nbWinningNumbers = 0;
            for (Integer n : card.numbers) {
                if (card.winningNumbers.contains(n)) {
                    nbWinningNumbers++;
                }
            }
            res += (int) Math.pow(2, nbWinningNumbers - 1);
        }
        return String.valueOf(res);
    }

    @Override
    public String solvePart2(String rawInput) {
        throw new NotImplementedException("Solve me !");
    }

}

