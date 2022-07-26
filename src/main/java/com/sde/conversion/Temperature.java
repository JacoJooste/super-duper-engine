package com.sde.conversion;

import java.util.function.Function;

public class Temperature extends Conversion {

    private final Unit[] units = new Unit[]{
            new Unit("Celsius", "C", 1.0d),
            new Unit("Kelvin", "K", 1.0d),
            new Unit("Fahrenheit", "F", new Function<Object, Double>() {
                @Override
                public Double apply(Object o) {
                    return 5d/9d;
                }
            }),
    };

    @Override
    public Unit[] getUnits() {
        return units;
    }

    @Override
    public String convert(String from, String to, String value) throws Exception {
        double fromValue = Double.parseDouble(value);
        Double toValue = null;
        Double valueInCelsius = null;
        for (Unit unit : units) {
            if (unit.getAbbreviation().equalsIgnoreCase(from)) {
                if (from.equalsIgnoreCase("K")) {
                    fromValue = fromValue - 273.15d;
                }
                if (from.equalsIgnoreCase("F")) {
                    fromValue = fromValue - 32d;
                }
                valueInCelsius = fromValue * unit.getConversionConstant();
                break;
            }
        }
        if (valueInCelsius == null) {
            throw new Exception("Conversion from unit '" + from + "' not found.");
        }
        for (Unit unit : units) {
            if (unit.getAbbreviation().equalsIgnoreCase(to)) {
                toValue = valueInCelsius / unit.getConversionConstant();
                if (to.equalsIgnoreCase("K")) {
                    toValue = toValue + 273.15d;
                }
                if (to.equalsIgnoreCase("F")) {
                    toValue = toValue + 32d;
                }
            }
        }
        if (toValue == null) {
            throw new Exception("Conversion to unit '" + to + "' not found.");
        }
        return toValue.toString();
    }
}
