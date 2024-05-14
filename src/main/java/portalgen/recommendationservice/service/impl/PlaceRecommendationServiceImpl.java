package portalgen.recommendationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import portalgen.recommendationservice.configuration.ConfigurationUtils;
import portalgen.recommendationservice.exception.BadRequestError;
import portalgen.recommendationservice.exception.InternalServerError;
import portalgen.recommendationservice.exception.ResponseException;
import portalgen.recommendationservice.model.response.*;
import portalgen.recommendationservice.service.RequestSender;
import portalgen.recommendationservice.service.PlaceRecommendationService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceRecommendationServiceImpl implements PlaceRecommendationService {
   private final RequestSender requestSender;
   private final ConfigurationUtils configurationUtils;
   private final ObjectMapper objectMapper;
   @Autowired
   private RedisTemplate<String, Object> redisTemplate;
    @Override
    public String requestRecommendedPlaces(String userId, String cityName, String country) throws JsonProcessingException {
        UserPlacePreferenceResponse userPlacePreference = getUserPlacePreference(userId);
        List<PlaceScoresResponse> placeScores = getPlaceScoresByCityName(cityName);

        requestSender.sendRequest(userPlacePreference, placeScores);

        return "Request sent, polling for results";
    }

    @Override
    public List<PlaceRecommendationResult> getPlaceRecommendations(String userId, String cityName, String country) {
        String key = configurationUtils.getRedisKeyPrefix() + userId + ":" + cityName + ":" + country;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        String rawResponse = (String) ops.get(key);
        if (rawResponse == null) {
            throw new ResponseException(BadRequestError.RECOMMENDATION_NOT_FOUND, "Recommendation not found for user" + userId);
        } else {
            return convertMessageToResponse(rawResponse);
        }
    }


    private UserPlacePreferenceResponse getUserPlacePreference(String userId) {
        String urlUser = configurationUtils.getUserServiceUrl() + "/api/v1/place_preference/user/" + userId;

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(urlUser)
                .encode()
                .toUriString();

        ResponseEntity<Response<UserPlacePreferenceResponse>> response;

        try {
            RestTemplate restTemplate = new RestTemplate();
           response = restTemplate.exchange(
                    urlTemplate,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<UserPlacePreferenceResponse>>() {
                    }
            );
        } catch (Exception ex) {
            if (ex instanceof HttpClientErrorException) {
                throw new ResponseException(BadRequestError.USER_PROFILE_NOT_FOUND);
            }

            throw ex;
        }

        return Objects.requireNonNull(response.getBody()).getData();
    }

    private List<PlaceScoresResponse> getPlaceScoresByCityName(String cityName) {
        String urlPlace = configurationUtils.getClassificationScoreServiceUrl() + "/api/v1/place_scores/city_name/" + cityName;

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(urlPlace)
                .encode()
                .toUriString();

        ResponseEntity<Response<List<PlaceScoresResponse>>> response;

        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(
                    urlTemplate,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<List<PlaceScoresResponse>>>() {
                    }
            );
        } catch (Exception ex) {
            if (ex instanceof HttpClientErrorException) {
                throw new ResponseException(BadRequestError.PLACE_SCORES_NOT_FOUND);
            }

            throw ex;
        }

        return Objects.requireNonNull(response.getBody()).getData();
    }

    private PlaceDetails getPlaceDetails(Long placeId) {
        String urlPlace = configurationUtils.getPlaceServiceUrl() + "/api/v1/place/" + placeId;

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(urlPlace)
                .encode()
                .toUriString();

        ResponseEntity<Response<PlaceDetails>> response;

        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.exchange(
                    urlTemplate,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<PlaceDetails>>() {
                    }
            );
        } catch (Exception ex) {
            if (ex instanceof HttpClientErrorException) {
                throw new ResponseException(BadRequestError.PLACE_NOT_FOUND);
            } else {
                throw new ResponseException(InternalServerError.INTERNAL_SERVER_ERROR);
            }
        }

        return Objects.requireNonNull(response.getBody()).getData();
    }

    private List<PlaceRecommendationResult> convertMessageToResponse(String response) {
        try {
            // Replace single quotes with double quotes
            String validJson = response.replace("'", "\"");

            List<PlaceRecommendationResponse> placeRecommendationResponses = objectMapper.readValue(validJson, new TypeReference<List<PlaceRecommendationResponse>>() {
            });

            return placeRecommendationResponses.stream()
                    .map(placeRecommendationResponse -> {
                        PlaceDetails placeDetails = this.getPlaceDetails(placeRecommendationResponse.getPlaceId());
                        return new PlaceRecommendationResult(placeDetails, placeRecommendationResponse.getScore());
                    })
                    .toList();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
