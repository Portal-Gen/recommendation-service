package portalgen.recommendationservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecommendationResponse {
    private Long userId;
    private String cityName;
    private String country;
    private List<PlaceRecommendationResponse> recommendations;
}
