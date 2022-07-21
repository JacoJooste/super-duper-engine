package com.sde.conversion;

public class Length extends Conversion {

    private final Unit[] units = new Unit[] {
        new Unit("Millimeter", "mm", 0.001),
        new Unit("Meter", "m", 1),
        new Unit("Kilometer", "km", 1000),
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
