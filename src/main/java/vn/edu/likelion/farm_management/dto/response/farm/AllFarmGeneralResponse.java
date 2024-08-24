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
public class AllFarmGeneralResponse {

    Double areaPlanted; // Diện tích đã trồng

    Double area; // Tổng diện tích nông trại

    Double yieldTotal; // Sản lượng dự kiến thu được

    Double priceTotal; // Tổng doanh thu dự kiến thu được


}
