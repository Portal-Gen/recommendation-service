package portalgen.recommendationservice.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum PriceLevel {
    UNKNOWN(0, "UNKNOWN"),
    FREE(1, "FREE"),
    CHEAP(2, "CHEAP"),
    MODERATE(3, "MODERATE"),
    EXPENSIVE(4, "EXPENSIVE"),
    VERY_EXPENSIVE(5, "VERY_EXPENSIVE");

    private final Integer code;
    private final String value;
    private static Map<Integer, PriceLevel> mapping;
    static {
        initMapping();
    }
    PriceLevel(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    public static PriceLevel getEnum(Integer code) {
        if (mapping == null) {
            initMapping();
        }
        PriceLevel priceLevel = mapping.get(code);
        if (priceLevel == null) {
            return PriceLevel.UNKNOWN;
        } else {
            return priceLevel;
        }
    }

    private static void initMapping() {
        mapping = new HashMap<>();
        for (PriceLevel value : values()) {
            mapping.put(value.getCode(), value);
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}

