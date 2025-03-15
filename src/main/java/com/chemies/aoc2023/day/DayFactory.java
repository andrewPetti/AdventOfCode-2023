package com.chemies.aoc2023.day;


public class DayFactory {

    public static Day getDay(final int day) {
        return switch (day) {
            case 1 -> new Day01();
            case 2 -> new Day02();
            //return new Day03();
            case 3, 4 -> new Day04();
            case 5 -> new Day05();
            case 6 -> new Day06();
            case 8, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25 -> new UnimplementedDay(day);
            default -> new UnimplementedDay(day);
        };

    }
}

