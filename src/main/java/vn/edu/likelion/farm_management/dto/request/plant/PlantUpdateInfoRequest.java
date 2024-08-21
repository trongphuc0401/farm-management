package vn.edu.likelion.farm_management.dto.request.plant;

import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * PlantUpdateInfoRequest -
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
public class PlantUpdateInfoRequest {

    String typePlantId; // ID loại cây trồng

    String name; // Tên cây trồng

    String description; // Mô tả cây trồng

    Double area; // Diện tích cây trồng trong 1 ô

    Double expectedYield; // Sản lượng dự kiến

    Double price; // Giá cả

    Integer seedlingDay; // Ngày bán

    Integer vegetativeStageDay; // Ngày bắt đầu giai đoạn sinh trưởng

    Integer floweringStageDay; // Ngày bắt đầu giai đoạn ra hoa

    Integer fruitingStageDay; // Ngày bắt đầu giai đoạn tạo quả
}