package com.sde.conversion;

public class Unit {
    private final String name;
    private final String abbreviation;
    private final double conversionConstant;

    public Unit(String name, String abbreviation, double conversionConstant) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.conversionConstant = conversionConstant;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public double getConversionConstant() {
        return conversionConstant;
    }
}
