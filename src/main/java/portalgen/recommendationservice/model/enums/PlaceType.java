package portalgen.recommendationservice.model.enums;

import portalgen.recommendationservice.exception.BadRequestError;
import portalgen.recommendationservice.exception.ResponseException;

import java.util.HashMap;
import java.util.Map;

public enum PlaceType {
    AMUSEMENT_CENTER(0, "amusement_center"),
    AMUSEMENT_PARK(1, "amusement_park"),
    AQUARIUM(2, "aquarium"),
    ART_GALLERY(3, "art_gallery"),
    BEACH(4, "beach"),
    BRIDGE(5, "bridge"),
    CANYON(6, "canyon"),
    CASTLE(7, "castle"),
    CAVE(8, "cave"),
    CHURCH(9, "church"),
    CITY(10, "city"),
    COUNTRYSIDE(11, "countryside"),
    CULTURAL_CENTER(12, "cultural_center"),
    FARM(13, "farm"),
    FOREST(14, "forest"),
    GARDEN(15, "garden"),
    HIKING_AREA(16, "hiking_area"),
    HISTORICAL_SITE(17, "historical_site"),
    ISLAND(18, "island"),
    LAKE(19, "lake"),
    LIGHTHOUSE(20, "lighthouse"),
    MANSION(21, "mansion"),
    MARKET(22, "market"),
    MONUMENT(23, "monument"),
    MOSQUE(24, "mosque"),
    MOUNTAIN(25, "mountain"),
    MUSEUM(26, "museum"),
    NATIONAL_PARK(27, "national_park"),
    OBSERVATORY(28, "observatory"),
    PALACE(29, "palace"),
    PARK(30, "park"),
    PERFORMING_ARTS(31, "performing_arts"),
    RIVER(32, "river"),
    STADIUM(33, "stadium"),
    TEMPLE(34, "temple"),
    THEATER(35, "theater"),
    TOWER(36, "tower"),
    TOURIST_ATTRACTION(37, "tourist_attraction"),
    VOLCANO(38, "volcano"),
    WATERFALL(39, "waterfall"),
    ZOO(40, "zoo");

    private final Integer code;
    private final String value;
    private static Map<Integer, PlaceType> mapping;
    static {
        initMapping();
    }
    PlaceType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }
    private static void initMapping() {
        mapping = new HashMap<>();
        for (PlaceType value : values()) {
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
