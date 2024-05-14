package portalgen.recommendationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import portalgen.recommendationservice.configuration.ConfigurationUtils;
import portalgen.recommendationservice.model.response.RecommendationResponse;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RecommendationResultListener {
    private final ConfigurationUtils configurationUtils;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "recommendation-response-queue")
    public void processResponse(String response) throws JsonProcessingException {
        // Replace single quotes with double quotes to fix JSON format
        String validJson = response.replace("'", "\"");

        // Now parse the valid JSON
        RecommendationResponse recommendationResponse = objectMapper.readValue(validJson, RecommendationResponse.class);

        String key = configurationUtils.getRedisKeyPrefix() + recommendationResponse.getUserId() + ":" + recommendationResponse.getCityName() + ":" + recommendationResponse.getCountry();
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();

        String jsonRecommendations = objectMapper.writeValueAsString(recommendationResponse.getRecommendations());
        ops.set(key, jsonRecommendations, 10, TimeUnit.MINUTES);
    }
}
