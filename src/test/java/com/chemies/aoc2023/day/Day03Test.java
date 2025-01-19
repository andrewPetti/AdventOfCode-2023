package com.chemies.aoc2023.day;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    private Day03 _subject;

    @BeforeEach
    void setup() {
        _subject = new Day03();
    }

    @Test
    void executePartA_expectCorrectResult() {
        final Integer expected = 4361;
        assertEquals(expected, _subject.partA("day03Sample.txt"));
    }

    @Test
    void partB_expectCorrectResult() {
        final int expected = 1;
        assertEquals(expected, _subject.partB("day03Sample.txt"));
    }
}
