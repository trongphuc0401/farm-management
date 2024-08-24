package vn.edu.likelion.farm_management.dto.response.dashboard;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestReport {

    String plantId;             // ID của cây trồng
    String plantName;           // Tên cây trồng
    String farmId;              // ID của nông trại
    String farmName;            // Tên nông trại
    String typePlantId;       // Loại cây trồng
    String typePlantName;       // Tên loại cây trồng
    Double totalYieldPlanted;   // Tổng sản lượng dự kiến
    Double totalMoneyPlanted;   // Tổng số tiền dự kiến
    Double totalYieldActual;    // Tổng sản lượng thực tế
    Double totalMoneyActual;    // Tổng số tiền thực tế
}
