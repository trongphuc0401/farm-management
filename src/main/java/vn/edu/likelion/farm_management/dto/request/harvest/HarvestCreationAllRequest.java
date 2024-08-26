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
    String farmId;

    String description;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.1", inclusive = true, message = "PARAM_OVER_MIN")
    Double yieldActual;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "1000.00", inclusive = true, message = "PARAM_OVER_MIN")
    Double priceActual;
}
