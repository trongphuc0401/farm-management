package vn.edu.likelion.farm_management.dto.response.plant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * PlantResponse -
 *
 * @param
 * @return
 * @throws
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantResponse {

    Long id; // ID của chi tiết cây trồng

    String typePlantId; // ID loại cây trồng

    String farmId; // ID nông trại

    String name; // Tên cây trồng

    String description; // Mô tả cây trồng

    Double area; // Diện tích cây trồng

    Double expectedYield; // Sản lượng dự kiến

    Double price; // Giá cả

    int status; // Trạng thái cây trồng

    String statusName; // Tên trạng thái

    Integer seedlingDay; // Ngày bán

    Integer vegetativeStageDay; // Ngày bắt đầu giai đoạn sinh trưởng

    Integer floweringStageDay; // Ngày bắt đầu giai đoạn ra hoa

    Integer fruitingStageDay; // Ngày bắt đầu giai đoạn tạo quả

    LocalDateTime datePlanted; // Ngày trồng

    LocalDateTime dateSeedlingFinish; // Ngày hoàn thành bán

    LocalDateTime dateVegetativeStageFinish; // Ngày hoàn thành giai đoạn sinh trưởng

    LocalDateTime dateFloweringStageFinish; // Ngày hoàn thành giai đoạn ra hoa

    LocalDateTime dateFruitingStageFinish; // Ngày hoàn thành giai đoạn tạo quả

    LocalDateTime dateHarvestFinish; // Ngày hoàn thành thu hoạch

    int isDeleted; // Trạng thái xóa mềm

    LocalDateTime createAt; // Ngày tạo

    LocalDateTime updateAt;
}