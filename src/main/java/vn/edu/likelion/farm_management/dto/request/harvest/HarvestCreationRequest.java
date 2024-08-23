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

    String plantId; // ID của cây trồng
    String plantName; // Tên cây trồng
    String typePlantId; // ID loại cây trồng
    String farmId; // ID nông trại
    String farmName; // Tên nông trại
    String description; // Mô tả cây trồng
    Double yieldTotal; // Sản lượng hiện tại
    Double priceCurrently; // Giá hiện tại

}
