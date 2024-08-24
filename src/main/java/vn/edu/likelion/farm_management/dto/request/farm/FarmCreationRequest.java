package vn.edu.likelion.farm_management.dto.request.farm;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * FarmCreationRequest -
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
public class FarmCreationRequest {

    @NotNull(message = "PARAM_NOT_NULL")
    @Size(max = 30, message = "")
    String name;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    @DecimalMax(value = "99999.0", inclusive = true, message = "AREA_TOO_LARGE")
    Double area;

    String status;
    String description;
}
