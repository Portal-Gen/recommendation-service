package portalgen.recommendationservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import portalgen.recommendationservice.model.enums.PlaceType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class UserPlacePreferenceResponse {
    private Long userProfileId;
    private Map<PlaceType, Float> preferences;

    public String toString() {
        return "UserPlacePreferenceResponse{" +
                "userProfileId=" + userProfileId +
                ", preferences=" + preferences +
                '}';
    }
}
