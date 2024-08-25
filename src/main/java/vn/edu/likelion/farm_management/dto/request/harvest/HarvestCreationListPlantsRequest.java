package vn.edu.likelion.farm_management.dto.request.harvest;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * HarvestCreationListPlantsRequest -
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
public class HarvestCreationListPlantsRequest {
    @NotNull(message = "PARAM_NOT_NULL")
     List<String> plantIds;
    @NotNull(message = "PARAM_NOT_NULL")
     HarvestCreationAllRequest harvestCreationAllRequest;
}
