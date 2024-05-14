package portalgen.recommendationservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceRecommendationResponse {
    private Long placeId;
    private Float score;
}
