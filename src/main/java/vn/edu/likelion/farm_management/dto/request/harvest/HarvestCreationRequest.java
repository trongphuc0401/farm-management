package vn.edu.likelion.farm_management.dto.request.harvest;


import jakarta.persistence.Column;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * HarvestCreationRequest - 
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
public class HarvestCreationRequest {
    Integer quantity;

    @NotNull(message = "PARAM_NOT_NULL")
    String farmId;

    String description;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "PARAM_NEGATIVE")
    Double yieldActual;

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "PARAM_NEGATIVE")
    Double priceActual;
}
