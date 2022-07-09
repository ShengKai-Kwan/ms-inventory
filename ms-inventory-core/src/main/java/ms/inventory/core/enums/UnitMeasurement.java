package ms.inventory.core.enums;

import com.dev.core.lib.utility.core.exception.GenericErrorException;
import ms.inventory.core.exception.InventoryError;

import java.util.Arrays;

public enum UnitMeasurement {
    PIECE,
    GRAM,
    KILOGRAM;

    public static UnitMeasurement findByValue(String unitMeasurement){
        return Arrays.stream(UnitMeasurement.values())
                .filter(s -> s.toString().equals(unitMeasurement))
                .findFirst()
                .orElseThrow(() -> new GenericErrorException(InventoryError.UNIT_MEASUREMENT_ENUM_VALUE_NOT_FOUND));
    }
}
