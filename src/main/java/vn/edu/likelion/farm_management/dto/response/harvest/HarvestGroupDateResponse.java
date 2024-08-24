package vn.edu.likelion.farm_management.dto.response.harvest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * HarvestGroupDateResponse -
 *
 * @param
 * @return
 * @throws
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HarvestGroupDateResponse {

    String date; // Ngày thu hoạch

    Double totalYieldActual; // Tổng sản lượng

    Double totalMoneyActual; // Tổng giá trị

    String description; // Mô tả cây trồng
    
}
