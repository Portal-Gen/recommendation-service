package portalgen.recommendationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import portalgen.recommendationservice.model.response.PlaceDetails;
import portalgen.recommendationservice.model.response.PlaceRecommendationResult;

import java.util.List;

public interface PlaceRecommendationService {
    String requestRecommendedPlaces(String userId, String cityName, String country) throws JsonProcessingException;

    List<PlaceRecommendationResult> getPlaceRecommendations(String userId, String cityName, String country);
}
