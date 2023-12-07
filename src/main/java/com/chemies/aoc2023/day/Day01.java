package com.chemies.aoc2023.day;


import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

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

    public int partA(final String filename) {
        ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        return stringList.stream()
                .mapToInt(this::getCalibarationNubmer)
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
        ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        return stringList.stream()
                .mapToInt(this::getCalibarationNubmerIncludeStrings)
                .sum();


    }

    private int getCalibarationNubmerIncludeStrings(String line){
        Matcher firstMatcher = Pattern.compile("\\d").matcher(line);

        Matcher secondMatcher = Pattern.compile("(\\d)(?!.*\\d)").matcher(line);

        String firstWord = "";
        int firstWordPos = line.length();
        ImmutableList<String> strings = ImmutableList.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");


        for(String value : strings) {
                Matcher matcher = Pattern.compile(value).matcher(line);
                if( matcher.find() && matcher.start() < firstWordPos) {
                    firstWord = value;
                    firstWordPos = matcher.start();
                }
        }


        String secondWord = "";
        int secondWordPos = -1;
        for (String value :strings){
            Matcher matcher = Pattern.compile(value).matcher(line);

            while(matcher.find()){
                if(matcher.start() > secondWordPos) {
                  secondWord = value;
                  secondWordPos = matcher.start();
                }
            }

        }
        String first;
        if (firstMatcher.find() && firstMatcher.start() < firstWordPos){
            first = firstMatcher.group();
        } else {
            first = convertWordToDigit(firstWord);
        }
        String second;
        if (secondMatcher.find() && secondMatcher.start() > secondWordPos){
            second = String.valueOf(secondMatcher.group().charAt(0));
        } else {
            second = convertWordToDigit(secondWord);
        }


        return Integer.valueOf(first + second);
    }

    private String convertWordToDigit(String firstWord) {
        switch (firstWord){
            case "one":
                return "1";
            case "two":
                return "2";
            case "three":
                return "3";
            case "four":
                return "4";
            case "five":
                return "5";
            case "six":
                return "6";
            case "seven":
                return "7";
            case "eight":
                return "8";
            case "nine":
                return "9";
            default:
                return "";
        }
    }
}
