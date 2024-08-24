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
public class MonthlyPlantHarvestSummaryDTO {

    int month;
    String typePlantId;
    String typePlantName; // Thêm trường này
    Double totalYieldPlanned;
    Double totalMoneyPlanned;
    Double totalYieldActual;
    Double totalMoneyActual;
}
