package com.chemies.aoc2023.day;


import com.chemies.aoc2023.util.TextFormatter;
import com.diogonunes.jcolor.Attribute;

public class UnimplementedDay implements Day {

    private final String _message;

    UnimplementedDay(final int requestedDay) {
        final TextFormatter _textFormatter = new TextFormatter();
        _message = "Day " + _textFormatter.format(requestedDay, Attribute.GREEN_TEXT()) + " not yet implemented!";
    }

    @Override
    public void executePartA() {
    }

    @Override
    public void executePartB() {
    }

    @Override
    public String getName() {
        return _message;
    }

    @Override
    public boolean canExecute() {
        return false;
    }
}