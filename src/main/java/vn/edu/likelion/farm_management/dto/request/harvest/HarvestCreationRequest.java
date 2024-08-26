package vn.edu.likelion.farm_management.dto.request.harvest;


import jakarta.persistence.Column;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @NotNull(message = "PARAM_NOT_NULL")
    @Min(value = 0 , message = "PARAM_OVER_MIN")
    @Max(value = 100,message = "PARAM_OVER_MAX")
    Integer quantity;

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
