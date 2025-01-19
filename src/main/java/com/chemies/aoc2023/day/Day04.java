package com.chemies.aoc2023.day;

import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
//        return 0;
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

        return Card.builder()
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

    private int partB(String file) {
        return 0;
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
