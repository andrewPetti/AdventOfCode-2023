package com.chemies.aoc2023.day;

import com.chemies.aoc2023.util.FileHelper;
import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.OptionalLong;

public class Day05 implements Day {
    private final FileHelper _fileHelper = new FileHelper();
    private final TextFormatter _textFormatter = new TextFormatter();

    @Override
    public void executePartA() {
        final long result = partA("day05Input.txt");
        System.out.println("Part A answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    long partA(String filename) {

        ImmutableList<ImmutableList<String>> groups = _fileHelper.fileToGroupedStringListTrim(filename);
        ImmutableList<String> seedsString = groups.getFirst();

        ImmutableList<Long> seeds = Arrays.stream(seedsString.getFirst().split(":")[1].trim().split(" "))
                .map(Long::parseLong)
                .collect(ImmutableList.toImmutableList());

        ImmutableList<ImmutableList<AlmanacMap>> almanacMaps = buildAlmanacMaps(groups);

        OptionalLong min = findMinimum(seeds, almanacMaps);
        return min.getAsLong();
    }

    private OptionalLong findMinimum(ImmutableList<Long> seeds, ImmutableList<ImmutableList<AlmanacMap>> almanacMaps) {
        return seeds.stream().mapToLong(seed -> findFinalLocation(seed, almanacMaps)).min();
    }

    private ImmutableList<ImmutableList<AlmanacMap>> buildAlmanacMaps(ImmutableList<ImmutableList<String>> groups) {
        ImmutableList.Builder<ImmutableList<AlmanacMap>> almanacMapsBuilder = new ImmutableList.Builder<>();
        for (int i = 1; i < groups.size(); i++) {
            almanacMapsBuilder.add(buildMap(groups.get(i)));
        }
        ImmutableList<ImmutableList<AlmanacMap>> almanacMaps = almanacMapsBuilder.build();
        return almanacMaps;
    }

    private Long findFinalLocation(Long seed, ImmutableList<ImmutableList<AlmanacMap>> almanacMaps) {
        Long val = seed;
        for (ImmutableList<AlmanacMap> almanac : almanacMaps) {
            val = findLocation(almanac, val);
        }
        return val;
    }

    private Long findLocation(ImmutableList<AlmanacMap> almanac, Long val) {
        AlmanacMap mapping = almanac.stream().filter(x -> {
                    if (x.source <= val && (x.source + x.range) >= val) {
                        return true;
                    } else {
                        return false;
                    }
                })
                .findFirst()
                .orElse(null);

        if (mapping == null) {
            return val;
        } else {
            long pos = val - mapping.source;
            return mapping.destination + pos;
        }
    }

    private ImmutableList<AlmanacMap> buildMap(ImmutableList<String> strings) {
        ImmutableList.Builder<AlmanacMap> almanacMapBuilder = new ImmutableList.Builder<>();
        for (int i = 1; i < strings.size(); i++) {
            String[] parts = strings.get(i).trim().split(" ");
            AlmanacMap.AlmanacMapBuilder builder = AlmanacMap.builder();
            builder.destination(Long.parseLong(parts[0]));
            builder.source(Long.parseLong(parts[1]));
            builder.range(Long.parseLong(parts[2]));
            almanacMapBuilder.add(builder.build());
        }

        return almanacMapBuilder.build();
    }

    @Override
    public void executePartB() {
        final long result = partB("day05Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    long partB(String filename) {


        ImmutableList<ImmutableList<String>> groups = _fileHelper.fileToGroupedStringListTrim(filename);
        ImmutableList<String> seedsString = groups.getFirst();

        String[] values = seedsString.getFirst().split(":")[1].trim().split(" ");
        ImmutableList.Builder<Long> seedsBuilder = new ImmutableList.Builder<Long>();
        long start = Long.parseLong(values[0]);
        long range = Long.parseLong(values[1]);
        for (long i = start; i < start + range; i++) {
            seedsBuilder.add(i);
        }
        start = Long.parseLong(values[2]);
        range = Long.parseLong(values[3]);
        for (long i = start; i < start + range; i++) {
            seedsBuilder.add(i);
        }
        //    .map(Long::parseLong)
//  .collect(ImmutableList.toImmutableList());
        ImmutableList<Long> seeds = seedsBuilder.build();
        ImmutableList<ImmutableList<AlmanacMap>> almanacMaps = buildAlmanacMaps(groups);

        OptionalLong min = findMinimum(seeds, almanacMaps);
        return min.getAsLong();
    }

    @Override
    public String getName() {
        return "Day 5";
    }

    @Override
    public boolean canExecute() {
        return true;
    }
}
