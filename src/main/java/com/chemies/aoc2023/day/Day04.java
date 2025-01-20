package com.chemies.aoc2023.day;

import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public class Day04 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();


    @Override
    public void executePartA() {
        final int result = partA("day04Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    int partA(String filename) {
        ImmutableList<String> fileLines = _fileHelper.fileToStringList(filename);
        ImmutableList<Card> cards = buildCards(fileLines);

        return cards.stream().mapToInt(this::getCardValue).sum();
    }

    private ImmutableList<Card> buildCards(ImmutableList<String> fileLines) {

        ImmutableList<Card> cards = fileLines.stream()
                .map(line -> buildCard(line))
                .collect(ImmutableList.toImmutableList());
        return cards;
    }

    private int getCardValue(Card card) {
        int val = 0;

        for (Integer number : card.cardNumbers) {
            if (card.winningNumbers.contains(number)) {
                if (val == 0) {
                    val = 1;
                } else {
                    val = val * 2;
                }
            }
        }
        return val;
    }

    private Card buildCard(String line) {
        String[] data = line.split(":");

        String[] numbers = data[1].split("\\|");
        String[] cardNumber = data[0].replaceAll("\\s{2,}"," ").split(" ");

        return Card.builder()
                .cardNumber(Integer.parseInt(cardNumber[1].replace("\\s{2,"," ").trim()))
                .winningNumbers(getNumbers(numbers[0]))
                .cardNumbers(getNumbers(numbers[1])).build();
    }

    private static ImmutableList<Integer> getNumbers(String line) {
        return Arrays.stream(line.trim().replaceAll("\\s{2,}", " ").split(" "))
                .map(Integer::parseInt)
                .collect(ImmutableList.toImmutableList());
    }

    @Override
    public void executePartB() {
        final int result = partB("day04Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    int partB(String filename) {
        ImmutableList<String> fileLines = _fileHelper.fileToStringList(filename);
        ImmutableList<Card> cards = buildCards(fileLines);

        int[] results = new int[cards.size()];

        cards.forEach(card -> {
                    int wins = getNumberOfWins(card);
                    int pos = card.cardNumber - 1;
                    results[pos]++;
                    if (wins > 0) {
                        for (int x = pos + 1; x <= pos + wins; x++) {
                            if (x < cards.size()) {
                                results[x] += results[pos];
                            }
                        }
                    }
                }
        );

        return Arrays.stream(results).sum();
    }

    private int getNumberOfWins(Card card) {
        return card.cardNumbers.stream().mapToInt(x -> {
            if (card.winningNumbers.contains(x)) {
                return 1;
            } else {
                return 0;
            }
        }).sum();
    }

    @Override
    public String getName() {
        return "Day 4";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
