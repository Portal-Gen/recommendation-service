package portalgen.recommendationservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceDetails {
    private Long id;
    private String googlePlaceId;
    private String displayName;
    private String formattedAddress;
    private float latitude;
    private float longitude;
    private float rating;
    private int userRatingCount;
    private String priceLevel;
    private String cityName;
    private String websiteUri;
    private String createdAt;
    private String updatedAt;
}
