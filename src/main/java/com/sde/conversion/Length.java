package com.sde.conversion;

public class Length extends Conversion {

    private final Unit[] units = new Unit[]{
            new Unit("Nanometer", "nm", 0.000000001d),
            new Unit("Micrometer", "um", 0.000001d),
            new Unit("Millimeter", "mm", 0.001d),
            new Unit("Centimeter", "cm", 0.01d),
            new Unit("Meter", "m", 1.0d),
            new Unit("Kilometer", "km", 1000.0d),
            new Unit("Light Year", "LY", 9460660000000000.0d),

            new Unit("Inches", "in", 0.0254d),
            new Unit("Feet", "ft", 0.3048d),
            new Unit("Yard", "yd", 0.9144d),
            new Unit("Mile", "mi", 1609.34d),
    };

    @Override
    public Unit[] getUnits() {
        return units;
    }

    @Override
    public String convert(String from, String to, String value) throws Exception {
        double fromValue = Double.parseDouble(value);
        Double toValue = null;
        Double valueInMeters = null;
        for (Unit unit : units) {
            if (unit.getAbbreviation().equalsIgnoreCase(from)) {
                valueInMeters = fromValue * unit.getConversionConstant();
                break;
            }
        }
        if (valueInMeters == null) {
            throw new Exception("Conversion from unit '" + from + "' not found.");
        }
        for (Unit unit : units) {
            if (unit.getAbbreviation().equalsIgnoreCase(to)) {
                toValue = valueInMeters / unit.getConversionConstant();
            }
        }
        if (toValue == null) {
            throw new Exception("Conversion to unit '" + to + "' not found.");
        }
        return toValue.toString();
    }
}
