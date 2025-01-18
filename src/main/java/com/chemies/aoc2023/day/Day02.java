package com.chemies.aoc2023.day;


import com.chemies.aoc2023.day.CubeSamples.CubeSamplesBuilder;
import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.OptionalInt;

public class Day02 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final int result = partA("day02Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    @Override
    public void executePartB() {
        final int result = partB("day02Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));
    }

    public int partA(final String filename) {
        final ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        final ImmutableList<ImmutableList<CubeSamples>> games = getGames(stringList);

        int sum = 0;
        for (int i = 1; i<=games.size(); i++){
            if(validGame(games.get(i-1))){
                sum += i;
            }

        }

        return sum;
    }

    private boolean validGame(ImmutableList<CubeSamples> cubeSamples) {
        for(CubeSamples sample : cubeSamples){
            if ( sample.blue > 14 || sample.red > 12 || sample.green > 13 ){
                return false;
            }
        }

        return true;
    }

    private ImmutableList<ImmutableList<CubeSamples>> getGames(final ImmutableList<String> stringList) {
        //final ArrayList<ArrayList<CubeSamples>> games = new ArrayList<>();
ImmutableList.Builder<ImmutableList<CubeSamples>> games = new ImmutableList.Builder<>();
        // final cubeSamples cube1 = cubeSamples.builder().blue(5).red(3).build();

        stringList.forEach(line -> {
            final String game = line.split(":")[1];
            final ImmutableList<String> draws = Arrays.stream(game.split(";"))
                    .collect(ImmutableList.toImmutableList());
            ImmutableList.Builder<CubeSamples> samples = new ImmutableList.Builder<>();
            draws.forEach(draw -> {
                final ImmutableList<String> sample = Arrays.stream(draw.split(",")).
                        collect(ImmutableList.toImmutableList());

                final CubeSamplesBuilder builder = CubeSamples.builder();
                sample.forEach(cube -> {
                    final String[] values = cube.trim().split(" ");

                    if (values[1].equals("blue")) {
                        builder.blue(Integer.parseInt(values[0]));
                    } else if (values[1].equals("red")) {
                        builder.red(Integer.parseInt(values[0]));
                    } else {
                        builder.green(Integer.parseInt(values[0]));
                    }
                });
                    samples.add(builder.build());
            });
            games.add(samples.build());
        });

        return games.build();
    }

    public int partB(final String filename) {
        final ImmutableList<String> stringList = _fileHelper.fileToStringList(filename);

        final ImmutableList<ImmutableList<CubeSamples>> games = getGames(stringList);

        return games.stream().mapToInt(this::getMinimumPower).sum();

    }

    private int getMinimumPower(ImmutableList<CubeSamples> game) {
        OptionalInt maxBlue = game.stream().mapToInt(x -> x.blue).max();
        OptionalInt maxRed = game.stream().mapToInt(x -> x.red).max();
        OptionalInt maxGreen = game.stream().mapToInt(x -> x.green).max();

        return maxBlue.getAsInt() * maxRed.getAsInt() * maxGreen.getAsInt();
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

