package vn.edu.likelion.farm_management.dto.response.harvest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * HarvestResponse -
 *
 * @param
 * @return
 * @throws
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestResponse {

    String id; // ID của chi tiết cây trồng

    String plantId; // ID cây trồng

    String plantName; // Tên cây trồng

    String typePlantId; // Id Loại cây trồng

    String farmId; // ID nông trại

    String farmName; // Tên nông trại

    String description; // Mô tả cây trồng

    Double yieldActual; // Sản lượng hiện tại

    Double priceActual; // Giá hiện tại

    Integer isDeleted; // Trạng thái xóa mềm

    LocalDateTime createAt; // Ngày tạo

    LocalDateTime updateAt; // Ngày cập nhật
}
