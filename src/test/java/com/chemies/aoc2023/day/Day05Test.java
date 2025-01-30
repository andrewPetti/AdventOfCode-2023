package com.chemies.aoc2023.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day05Test {
    private Day05 _subject;

    @BeforeEach
    void setup() {
        _subject = new Day05();
    }

    @Test
    void executePartA_expectCorrectResult() {
        final long expected = 35;
        assertEquals(expected, _subject.partA("day05Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final int expected = 30;
        assertEquals(expected, _subject.partB("day05Sample.txt"));
    }
}