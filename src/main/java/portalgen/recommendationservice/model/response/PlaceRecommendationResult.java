package portalgen.recommendationservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlaceRecommendationResult {
    private PlaceDetails placeDetails;
    private Float score;
}
