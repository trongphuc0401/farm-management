package vn.edu.likelion.farm_management.dto.request.harvest;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * HarvestCreationAllRequest -
 *
 * @param
 * @return
 * @throws
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestCreationAllRequest {
    String plantId;
    String plantName;
    String typePlantId;
    String farmId;
    String farmName;
    String description;
    Integer totalYield;
    Double priceCurrently;
}
