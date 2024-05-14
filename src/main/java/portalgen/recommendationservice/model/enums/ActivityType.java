package portalgen.recommendationservice.model.enums;

import java.util.HashMap;
import java.util.Map;

public enum ActivityType {
    SEARCH_DESTINATION(0, "SEARCH_DESTINATION"),
    BOOK_ACTIVITY(1, "BOOK_ACTIVITY"),
    VIEW_OR_CLICK_CONTENT(2, "VIEW_OR_CLICK_CONTENT"),
    RATE_DESTINATION(3, "RATE_DESTINATION"),
    SHARE_CONTENT(4, "SHARE_CONTENT"),
    INTERACT_RECOMMENDATION(5, "INTERACT_RECOMMENDATION"),
    COMPLETE_SURVEY(6, "COMPLETE_SURVEY"),
    INTERACT_SOCIAL_MEDIA_POST(7, "INTERACT_SOCIAL_MEDIA_POST");

    private final Integer code;
    private final String value;
    private static Map<Integer, ActivityType> mapping;
    static {
        initMapping();
    }
    ActivityType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    private static void initMapping() {
        mapping = new HashMap<>();
        for (ActivityType value : values()) {
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
