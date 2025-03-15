package com.chemies.aoc2023.day;

import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;

public class Day06 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final int result = partA("Day06Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    int partA(String filename) {

        ImmutableList<String> input = _fileHelper.fileToStringList(filename);
        String firstLine = input.getFirst();
        ImmutableList<Integer> times = processLine(firstLine);
        ImmutableList<Integer> distances = processLine(input.getLast());
        ArrayList<Integer> winningCounts = new ArrayList<>();
        for (int i = 0; i < times.size(); i++) {
            winningCounts.add(getWinningOptionsCountInt(times.get(i), distances.get(i)));
        }
        int result = winningCounts.get(0) * winningCounts.get(1);
        for (int j = 2; j < winningCounts.size(); j++) {
            result *= winningCounts.get(j);
        }
        return result;
    }

    private int getWinningOptionsCountInt(int time, int distance) {
        int winning = 0;
        for (int i = 1; i <= time; i++) {
            int availableTime = time - i;
            if (availableTime * i > distance) {
                winning++;
            }
        }
        return winning;
    }

    private Long getWinningOptionsCount(Long time, Long distance) {
        Long winning = 0L;
        for (long i = 1L; i <= time; i++) {
            long availableTime = time - i;
            if (availableTime * i > distance) {
                winning++;
            }
        }
        return winning;
    }

    private ImmutableList<Integer> processLine(String firstLine) {
        String[] split = firstLine.split(":");
        String values = split[1].trim().replaceAll(" +", " ");
        String[] vals = values.split(" ");
        ImmutableList.Builder<Integer> numbers = new ImmutableList.Builder<Integer>();

        for (String val : vals) {
            numbers.add(Integer.parseInt(val));
        }
        return numbers.build();
    }

    @Override
    public void executePartB() {
        final Long result = partB("Day06Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    Long partB(String filename) {
        ImmutableList<String> input = _fileHelper.fileToStringList(filename);
        String firstLine = input.getFirst();
        Long time = processSingleLine(firstLine);
        Long distance = processSingleLine(input.getLast());

        return getWinningOptionsCount(time, distance);
    }

    private Long processSingleLine(String firstLine) {
        String[] split = firstLine.split(":");
        String value = split[1].trim().replaceAll(" +", "");

        return Long.parseLong(value);
    }

    @Override
    public String getName() {
        return "Day06";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}