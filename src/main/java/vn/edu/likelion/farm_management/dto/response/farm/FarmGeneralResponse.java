package vn.edu.likelion.farm_management.dto.response.farm;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * FarmGeneralResponse -
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
public class FarmGeneralResponse {

    String id; // ID của chi tiết cây trồng

    String name; // Tên của chi tiết cây trồng

    String plantName; // Tên cây trồng

    String typePlantName; // Tên loại cây trồng

    Double areaPlanted; // Diện tích đã trồng

    Double area; // Tổng diện tích nông trại

    LocalDateTime dateHarvest; // Ngày thu hoạch

    int harvestablePlantCount; // Số lượng cây trồng có thể thu hoạch

    String status; // Trạng thái

    String description; // Mô tả

    Integer isDeleted; // Trạng thái xóa mềm

    LocalDateTime createAt; // Ngày tạo

    LocalDateTime updateAt; //
}
