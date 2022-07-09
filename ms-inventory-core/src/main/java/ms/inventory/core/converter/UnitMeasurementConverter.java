package ms.inventory.core.converter;


import ms.inventory.core.enums.UnitMeasurement;

import javax.persistence.AttributeConverter;

public class UnitMeasurementConverter implements AttributeConverter<UnitMeasurement, String> {

    @Override
    public String convertToDatabaseColumn(UnitMeasurement unitMeasurement){
        if(null == unitMeasurement)
            throw new NullPointerException("Missing Unit Measurement's Value");
        return unitMeasurement.toString();
    }

    @Override
    public UnitMeasurement convertToEntityAttribute(String status){
        return UnitMeasurement.findByValue(status);
    }
}
