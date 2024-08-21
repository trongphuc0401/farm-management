package vn.edu.likelion.farm_management.dto.response.farm;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FarmGeneralResponse {

    Long id; // ID của chi tiết cây trồng

    String name; // Tên của chi tiết cây trồng

    String plantName; // Tên cây trồng

    String typePlantName; // Tên loại cây trồng

    Double areaPlanted; // Diện tích đã trồng

    Double area; // Tổng diện tích nông trại

    LocalDateTime dateHarvest; // Ngày thu hoạch

    Double yieldQuantity; // Sản lượng

    String status; // Trạng thái

    Integer isDeleted; // Trạng thái xóa mềm

    LocalDateTime createAt; // Ngày tạo

    LocalDateTime updateAt; //
}
