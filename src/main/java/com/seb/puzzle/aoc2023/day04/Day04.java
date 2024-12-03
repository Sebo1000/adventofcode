package com.seb.puzzle.aoc2023.day04;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzlePart;
import com.seb.core.PuzzleRunner;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Day04 extends AbstractPuzzle {

    public static void main(String[] args) {
        PuzzleRunner.run(new Day04());
    }

    private final List<Card> cards = new ArrayList<>();

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
        String[] lines = splitInputLines(rawInput);
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
        log.info("nbCard {}", cards.size());
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
        List<Card> newCards = new ArrayList<>();

        for (Card card : cards) {
            int nbWinningNumbers = 0;
            for (Integer n : card.numbers) {
                if (card.winningNumbers.contains(n)) {
                    nbWinningNumbers++;
                }
            }
            card.nbWinningNumbers = nbWinningNumbers;
        }

        cards.forEach(c -> processCard(c, cards, newCards));
        return String.valueOf(newCards.size());
    }

    void processCard(Card card, List<Card> cards, List<Card> newCards) {
        newCards.add(card);

        for (int i = card.id; i < card.nbWinningNumbers + card.id; i++) {
            processCard(cards.get(i), cards, newCards);
        }
    }
}
