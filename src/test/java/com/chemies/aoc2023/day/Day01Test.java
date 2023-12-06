package com.chemies.aoc2023.day;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day01Test {

    private Day01 _subject;

    @BeforeEach
    void setup() {
        _subject = new Day01();
    }

    @Test
    void executePartA_expectCorrectResult() {
        final Integer expected = 142;
        assertEquals(expected, _subject.partA("day01Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final Integer expected = 45000;
        assertEquals(expected, _subject.partB("day01Sample.txt"));
    }
}