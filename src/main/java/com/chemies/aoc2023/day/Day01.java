package com.chemies.aoc2023.day;


import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 implements Day {

    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final int result = partA("day01Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    @Override
    public void executePartB() {
        final int result = partB("day01Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }


    @Override
    public String getName() {
        return "Day 1";
    }

    @Override
    public boolean canExecute() {
        return true;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public int partA(final String filename) {
        ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        return stringList.stream()
                .mapToInt( line -> getCalibarationNubmer(line))
                .sum();

    }

    private int getCalibarationNubmer(String line){
        Matcher firstMatcher = Pattern.compile("\\d").matcher(line);
        firstMatcher.find();

        Matcher secondMatcher = Pattern.compile("(\\d)(?!.*\\d)").matcher(line);
        secondMatcher.find();

        String first = firstMatcher.group();
        String second = secondMatcher.group();


        Integer integer = Integer.valueOf(first + second.charAt(0));
        return integer;
    }
    public int partB(final String filename) {

        ImmutableList<ImmutableList<Integer>> groupedList = _fileHelper.fileToGroupedIntegerList(filename);
        return groupedList.stream()
                .mapToInt(person -> person.stream()
                        .mapToInt(Integer::intValue).sum())
                .boxed()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
