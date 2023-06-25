package ms.inventory.svc.constant;

public class ResourcePath {
    public static final String APP_VERSION = "/v1";

    public static final String STOCK_PATH = "/stock";
    public static final String STOCK_GET_ALL = STOCK_PATH;
    public static final String STOCK_GET_BY_ID = STOCK_PATH + "/{id}";
    public static final String STOCK_UPSERT = STOCK_PATH;
    public static final String STOCK_DELETE = STOCK_PATH + "/{id}/delete";
}
