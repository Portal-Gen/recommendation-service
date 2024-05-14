package portalgen.recommendationservice.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ConfigurationUtils {
    @Value("${place.service.url}")
    private String placeServiceUrl;

    @Value("${classification-score.service.url}")
    private String classificationScoreServiceUrl;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${portalgen.redis.key.prefix}")
    private String redisKeyPrefix;
}
