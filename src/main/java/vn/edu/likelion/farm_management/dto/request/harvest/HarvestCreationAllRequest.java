package vn.edu.likelion.farm_management.dto.request.harvest;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "PARAM_NOT_NULL")
    String plantId;

    @NotNull(message = "PARAM_NOT_NULL")
    String plantName;

    @NotNull(message = "PARAM_NOT_NULL")
    String typePlantId;

    @NotNull(message = "PARAM_NOT_NULL")
    String farmId;

    @NotNull(message = "PARAM_NOT_NULL")
    String farmName;
    String description;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    Double yieldActual;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    Double priceActual;
}
