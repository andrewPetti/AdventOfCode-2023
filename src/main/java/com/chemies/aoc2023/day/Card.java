package com.chemies.aoc2023.day;

import com.google.common.collect.ImmutableList;
import lombok.Builder;

@Builder
public class Card {

    ImmutableList<Integer> winningNumbers;
    ImmutableList<Integer> cardNumbers;

    public static class cubeSamplesBuilder {
    }

}
