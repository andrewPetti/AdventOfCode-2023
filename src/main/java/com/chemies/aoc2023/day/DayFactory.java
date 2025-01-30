package com.chemies.aoc2023.day;


public class DayFactory {

    public static Day getDay(final int day) {
        switch (day) {
            case 1:
                return new Day01();
            case 2:
                return new Day02();
            case 3:
                //return new Day03();
            case 4:
                return new Day04();
            case 5:
                return new Day05();
            case 6:
                //return new Day06();
            case 8:
                //return new Day08();
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
                return new UnimplementedDay(day);
        }

        return new UnimplementedDay(day);
    }
}

