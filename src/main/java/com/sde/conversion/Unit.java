package com.sde.conversion;

import java.util.function.Function;

public class Unit {
    private final String name;
    private final String abbreviation;
    private final Double conversionConstant;
    private final Function<Object, Double> conversionFunction;

    public Unit(String name, String abbreviation, double conversionConstant) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.conversionConstant = conversionConstant;
        this.conversionFunction = o -> conversionConstant;
    }

    public Unit(String name, String abbreviation, Function<Object, Double> conversionFunction) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.conversionConstant = null;
        this.conversionFunction = conversionFunction;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public double getConversionConstant() {
        return conversionConstant == null ? conversionFunction.apply(null) : conversionConstant;
    }
}
