package portalgen.recommendationservice.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import portalgen.recommendationservice.model.enums.PlaceType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@RequiredArgsConstructor
public class PlaceScoresResponse {
    private String id;
    private String cityName;
    private String country;
    private Long placeId;
    private Map<PlaceType, Float> classificationScores;

    public String toString() {
        return "PlaceScoresResponse{" +
                "id='" + id + '\'' +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", placeId=" + placeId +
                ", classificationScores=" + classificationScores +
                '}';
    }
}
