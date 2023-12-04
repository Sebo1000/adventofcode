package com.seb.puzzle.day02;

import com.seb.core.AbstractPuzzle;
import com.seb.core.PuzzleRunner;
import com.seb.puzzle.day02.model.Game;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 extends AbstractPuzzle {

    ArrayList<Game> games = new ArrayList<Game>();

    @Override
    public boolean isSample() {
        return false;
    }

    @Override
    public void commonPart(final String rawInput) {
        final String regex = "(?<blueNb>\\d+)\\s+blue|(?<redNb>\\d+)\\s+red|(?<greenNb>\\d+)\\s+green";

        String[] lines = splitInputLines(rawInput);

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String[] split = line.split(":");
            Game game = new Game(i + 1);

            final Pattern pattern = Pattern.compile(regex);
            final Matcher matcher = pattern.matcher(split[1]);

            while (matcher.find()) {
                Integer redNb = extractNumber(matcher.group("redNb"));
                Integer greenNb = extractNumber(matcher.group("greenNb"));
                Integer blueNb = extractNumber(matcher.group("blueNb"));

                if (redNb != null && (game.maxRed == null || game.maxRed < redNb)) {
                    game.maxRed = redNb;
                }
                if (blueNb != null && (game.maxBlue == null || game.maxBlue < blueNb)) {
                    game.maxBlue = blueNb;
                }
                if (greenNb != null && (game.maxGreen == null || game.maxGreen < greenNb)) {
                    game.maxGreen = greenNb;
                }
            }
            games.add(game);
        }
    }

    @Override
    public String solvePart1(String rawInput) {
        final int maxRedColors = 12;
        final int maxGreenColors = 13;
        final int maxBlueColors = 14;

        int res = 0;
        for (Game game : games) {
            if (game.maxRed <= maxRedColors && game.maxGreen <= maxGreenColors && game.maxBlue <= maxBlueColors) {
                res += game.id;
            }
        }
        return String.valueOf(res);
    }

    @Override
    public String solvePart2(String rawInput) {
        int res = 0;
        for (Game game : games) {
            res += game.maxRed * game.maxGreen * game.maxBlue;
        }
        return String.valueOf(res);
    }


    private Integer extractNumber(String numberStr) {
        return (numberStr != null) ? Integer.valueOf(numberStr) : null;
    }

    public static void main(String[] args) {
        PuzzleRunner.run(new Day02());
    }

}
