package vn.edu.likelion.farm_management.dto.request.farm;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * FarmCreationRequest -
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
public class FarmCreationRequest {

    String name;
    Double area;
//    Double yieldQuantity;
    String status;
    String description;
}
