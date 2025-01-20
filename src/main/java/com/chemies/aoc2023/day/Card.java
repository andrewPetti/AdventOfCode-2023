package com.chemies.aoc2023.day;

import com.google.common.collect.ImmutableList;
import lombok.Builder;

@Builder
public class Card {

    Integer cardNumber;
    ImmutableList<Integer> winningNumbers;
    ImmutableList<Integer> cardNumbers;

    public static class cubeSamplesBuilder {
    }

}
