package com.chemies.aoc2023.util;


import com.google.common.collect.ImmutableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unused")
public class FileHelper {

    public static final String DATA_FOLDER = "\\src\\main\\java\\com\\chemies\\aoc2023\\data\\";

    public ImmutableList<String> fileToStringList(final String filename) {
        final ArrayList<String> list = new ArrayList<>();

        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }


    public ImmutableList<Integer> fileStringIntegerListToIntegerList(final String filename) {
        final ImmutableList<Integer> list;
        try {
            final BufferedReader reader = getReader(filename);
            final String line = reader.readLine().trim();
            list = Arrays.stream(line.split(","))
                    .map(Integer::parseInt)
                    .collect(ImmutableList.toImmutableList());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public ImmutableList<Integer> fileToIntegerList(final String filename) {
        final ArrayList<Integer> list = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(Integer.parseInt(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }

    public ImmutableList<Long> fileToLongList(final String filename) {
        final ArrayList<Long> list = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            while (line != null) {
                list.add(Long.parseLong(line));
                line = reader.readLine();
            }
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(list);
    }

    public ImmutableList<ImmutableList<String>> fileToGroupedStringListNoTrim(final String filename) {
        return fileToGroupedStringList(filename, false);
    }

    public ImmutableList<ImmutableList<String>> fileToGroupedStringListTrim(final String filename) {
        return fileToGroupedStringList(filename, true);
    }

    private ImmutableList<ImmutableList<String>> fileToGroupedStringList(final String filename, final Boolean trim) {
        final ArrayList<ImmutableList<String>> outerList = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line;
            if (trim) {
                line = reader.readLine().trim();
            } else {
                line = reader.readLine();
            }
            final ArrayList<String> innerList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                if (line == null || line.isEmpty()) {
                    outerList.add(ImmutableList.copyOf(innerList));
                    innerList.clear();
                    if (line == null) {
                        cont = false;
                    }
                } else {
                    innerList.add(line);
                }
                line = reader.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(outerList);
    }

    public ImmutableList<ImmutableList<Integer>> fileToGroupedIntegerList(final String filename) {
        final ArrayList<ImmutableList<Integer>> outerList = new ArrayList<>();
        try {
            final BufferedReader reader = getReader(filename);
            String line = reader.readLine().trim();
            final ArrayList<Integer> innerList = new ArrayList<>();
            boolean cont = true;
            while (cont) {
                if (line == null || line.isEmpty()) {
                    outerList.add(ImmutableList.copyOf(innerList));
                    innerList.clear();
                    if (line == null) {
                        cont = false;
                    }
                } else {
                    innerList.add(Integer.parseInt(line));
                }
                line = reader.readLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return ImmutableList.copyOf(outerList);
    }

    private BufferedReader getReader(final String filename) throws FileNotFoundException {
        final BufferedReader reader;
        try {
            String filePath = new File("").getAbsolutePath();

            reader =
                    new BufferedReader(new FileReader(filePath+ DATA_FOLDER + filename));

        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            throw new FileNotFoundException(e.getMessage());
        }

        return reader;
    }

    public String fileToString(final String filename) {

        String line = null;
        try {
            final BufferedReader reader = getReader(filename);
            line = reader.readLine().trim();
            reader.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
