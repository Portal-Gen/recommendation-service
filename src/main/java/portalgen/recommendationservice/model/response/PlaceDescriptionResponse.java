package portalgen.recommendationservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaceDescriptionResponse {
    private Long id;
    private String displayName;
    private String city;
    private String country;
    private String description;
}
