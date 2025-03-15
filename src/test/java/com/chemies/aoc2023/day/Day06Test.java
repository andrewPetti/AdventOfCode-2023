package com.chemies.aoc2023.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    private Day06 _subject;

    @BeforeEach
    void setUp() {
        _subject = new Day06();
    }

    @Test
    void partA_expectCorrectResult() {
        final int expected = 288;
        assertEquals(expected, _subject.partA("day06Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final Long expected = 71503L;
        assertEquals(expected, _subject.partB("day06Sample.txt"));
    }
}