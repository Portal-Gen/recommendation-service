package portalgen.recommendationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import portalgen.recommendationservice.model.response.PlaceDetails;
import portalgen.recommendationservice.model.response.PlaceRecommendationResult;
import portalgen.recommendationservice.model.response.Response;
import portalgen.recommendationservice.service.PlaceRecommendationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PlaceRecommendationController {
    private final PlaceRecommendationService placeRecommendationService;

    @GetMapping("/place-recommendations/request/user/{userId}/city/{cityName}/country/{country}")
    public Response<String> requestRecommendedPlaces(@PathVariable String userId, @PathVariable String cityName, @PathVariable String country) throws JsonProcessingException {
        return new Response<>(placeRecommendationService.requestRecommendedPlaces(userId, cityName, country));
    }

    @GetMapping("/place-recommendations/poll/user/{userId}/city/{cityName}/country/{country}")
    public Response<List<PlaceRecommendationResult>> pollRecommendations(
            @PathVariable String userId, @PathVariable String cityName, @PathVariable String country) throws JsonProcessingException {
        List<PlaceRecommendationResult> computedRecommendations = placeRecommendationService.getPlaceRecommendations(userId, cityName, country);

        return new Response<>(computedRecommendations);
    }
}
