package vn.edu.likelion.farm_management.dto.request.farm;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;

import java.util.List;

/**
 * FarmAddPlantRequest -
 *
 * @param
 * @return
 * @throws
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FarmAddNewPlantRequest {

    @NotNull
    @Min(value = 1, message = "PARAM_OVER_MIN")
    @Max(value = 1000, message = "PARAM_OVER_MAX")
    Integer quantity;

    @NotNull(message = "PARAM_NOT_NULL")
    String farmId;

    @NotNull
    PlantCreationRequest plantCreationRequest;

}
