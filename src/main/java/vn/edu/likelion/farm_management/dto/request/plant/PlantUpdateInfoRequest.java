package vn.edu.likelion.farm_management.dto.request.plant;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

//    String typePlantId; // ID loại cây trồng

    String name; // Tên cây trồng

    String description; // Mô tả cây trồng

    @DecimalMax(value = "99999.0", inclusive = true, message = "PARAM_OVER_MAX")
    @DecimalMin(value = "0.1", inclusive = true, message = "PARAM_OVER_MIN")
    Double area; // Diện tích cây trồng trong 1 ô

    @DecimalMin(value = "0.1", inclusive = true, message = "PARAM_OVER_MIN")
    Double yield; // Sản lượng dự kiến

    @DecimalMin(value = "0.1", inclusive = true, message = "PARAM_OVER_MIN")
    Double price; // Giá cả

    @Min(value = 1, message = "PARAM_OVER_MIN")
    Integer seedlingDay; // Ngày bán

    @Min(value = 1, message = "PARAM_OVER_MIN")
    Integer vegetativeStageDay; // Ngày bắt đầu giai đoạn sinh trưởng

    @Min(value = 1, message = "PARAM_OVER_MIN")
    Integer floweringStageDay; // Ngày bắt đầu giai đoạn ra hoa

    @Min(value = 1, message = "PARAM_OVER_MIN")
    Integer fruitingStageDay; // Ngày bắt đầu giai đoạn tạo quả
}