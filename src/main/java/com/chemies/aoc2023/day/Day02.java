package com.chemies.aoc2023.day;


import com.chemies.aoc2023.day.CubeSamples.CubeSamplesBuilder;
import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;

public class Day02 implements Day {
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

    public int partA(final String filename) {
        final ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        final ImmutableList<ImmutableList<String>> games = getGames(stringList);

        return 0;
    }

    private ImmutableList<ImmutableList<String>> getGames(final ImmutableList<String> stringList) {
        final ArrayList<ImmutableList<String>> games = new ArrayList<>();

        // final cubeSamples cube1 = cubeSamples.builder().blue(5).red(3).build();

        stringList.forEach(line -> {
            final String game = line.split(":")[1];
            final ImmutableList<String> draws = Arrays.stream(game.split(";"))
                    .collect(ImmutableList.toImmutableList());

            draws.stream().forEach(draw -> {
                final ImmutableList<String> sample = Arrays.stream(draw.split(",")).
                        collect(ImmutableList.toImmutableList());

                final CubeSamplesBuilder builder = CubeSamples.builder();
                sample.forEach(cube -> {
                    final String[] values = cube.split(" ");

                    if (values[1].equals("blue")) {
                        builder.blue(Integer.parseInt(values[0]));
                    } else if (values[1].equals("red")) {
                        builder.red(Integer.parseInt(values[0]));
                    } else {
                        builder.green(Integer.parseInt(values[0]));
                    }
                });
            });
        });

        return ImmutableList.copyOf(games);
    }

    public int partB(final String filename) {
        final ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        return 0;

    }

    @Override
    public String getName() {
        return "Day 2";
    }

    @Override
    public boolean canExecute() {
        return true;
    }


}

