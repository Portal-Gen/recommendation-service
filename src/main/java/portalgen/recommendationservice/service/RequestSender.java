package portalgen.recommendationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import portalgen.recommendationservice.configuration.RabbitMQConfig;
import portalgen.recommendationservice.model.response.PlaceScoresResponse;
import portalgen.recommendationservice.model.response.UserPlacePreferenceResponse;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RequestSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RequestSender(RabbitTemplate rabbitTemplate, ObjectMapper ObjectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = ObjectMapper;
    }

    public void sendRequest(UserPlacePreferenceResponse userPlacePreference, List<PlaceScoresResponse> placeScores) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(Arrays.asList(userPlacePreference, placeScores));
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchangeName, "requestRoutingKey", json, new CorrelationData(UUID.randomUUID().toString()));
    }
}

