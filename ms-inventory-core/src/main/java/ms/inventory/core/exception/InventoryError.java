package ms.inventory.core.exception;

import com.dev.core.lib.utility.core.exception.base.BaseError;

public enum InventoryError implements BaseError {
    UNIT_MEASUREMENT_ENUM_VALUE_NOT_FOUND("10001", "Unit Measurement Enum Value Not Found."),
    STOCK_RECORD_ALREADY_EXIST("11001", "Stock Record Already Exist."),
    STOCK_RECORD_NOT_FOUND("11002", "Stock Record Not Found."),
    STOCK_ID_NULL("11003", "Stock Id Is Required."),
    PRODUCT_RECORD_ALREADY_EXIST("12001", "Product Record Already Exist."),
    PRODUCT_RECORD_NOT_FOUND("12002", "Product Record Not Found."),
    PRODUCT_ID_NULL("12003", "Product Id Is Required.");

    private String application = "MS-INVENTORY";
    private String code;
    private String description;

    InventoryError(String code, String description){
        this.code = code;
        this.description = description;
    }

    @Override
    public String getApplication(){
        return this.application;
    }

    @Override
    public String getCode(){
        return this.code;
    }

    @Override
    public String getDescription(){
        return this.description;
    }
}
