package com.chemies.aoc2023.day;

import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

public class Day03 implements Day{
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final int result = partA("day03Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    int partA(String filename) {
        ImmutableList<String> fileLines = _fileHelper.fileToStringList(filename);
        String[] split = fileLines.get(0).split(".");
        return split.length;
    }

    @Override
    public void executePartB() {
        final int result = partB("day03Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    int partB(String file) {
        return 0;
    }

    @Override
    public String getName() {
        return "Day 3";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
