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

        ImmutableList.Builder<ImmutableList<AlmanacMap>> almanacMapsBuilder = new ImmutableList.Builder<>();
        for (int i = 1; i < groups.size(); i++) {
            almanacMapsBuilder.add(buildMap(groups.get(i)));
        }
        ImmutableList<ImmutableList<AlmanacMap>> almanacMaps = almanacMapsBuilder.build();

        OptionalLong min = seeds.stream().mapToLong(seed -> findFinalLocation(seed, almanacMaps)).min();
        return min.getAsLong();
        // return 0;
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
        final int result = partB("day05Input.txt");
        System.out.println("Part B answer: " + _textFormatter.format(result, Attribute.GREEN_TEXT()));

    }

    int partB(String filename) {
        return 0;
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
