package vn.edu.likelion.farm_management.dto.request.plant;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


/**
 * PlantCreationRequest -
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
public class PlantCreationRequest {

    @NotNull(message = "PARAM_NOT_NULL")
    String typePlantId; // ID loại cây trồng

    @NotNull(message = "PARAM_NOT_NULL")
    String name; // Tên cây trồng

    String description; // Mô tả cây trồng

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    @DecimalMax(value = "99999.0", inclusive = true, message = "AREA_TOO_LARGE")
    Double area; // Diện tích cây trồng

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    Double yield; // Sản lượng dự kiến

    @NotNull(message = "PARAM_NOT_NULL")
    @DecimalMin(value = "0.0", inclusive = true, message = "AREA_NEGATIVE")
    Double price; // Giá cả

    @NotNull(message = "PARAM_NOT_NULL")
    @Min(value = 0, message = "AREA_NEGATIVE")
    Integer seedlingDay; // Ngày bán

    @NotNull(message = "PARAM_NOT_NULL")
    @Min(value = 0, message = "AREA_NEGATIVE")
    Integer vegetativeStageDay; // Ngày bắt đầu giai đoạn sinh trưởng

    @NotNull(message = "PARAM_NOT_NULL")
    @Min(value = 0, message = "AREA_NEGATIVE")
    Integer floweringStageDay; // Ngày bắt đầu giai đoạn ra hoa

    @NotNull(message = "PARAM_NOT_NULL")
    @Min(value = 0, message = "AREA_NEGATIVE")
    Integer fruitingStageDay; // Ngày bắt đầu giai đoạn tạo quả
}