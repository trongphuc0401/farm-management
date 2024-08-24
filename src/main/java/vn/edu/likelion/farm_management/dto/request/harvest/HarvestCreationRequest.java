package vn.edu.likelion.farm_management.dto.request.harvest;


import jakarta.persistence.Column;

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
    String farmId;
    String description;
    Double yieldActual;
    Double priceActual;
}
