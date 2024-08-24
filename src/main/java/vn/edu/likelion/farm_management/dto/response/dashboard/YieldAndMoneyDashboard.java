package vn.edu.likelion.farm_management.dto.response.dashboard;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class YieldAndMoneyDashboard {

    List<MonthlyPlantHarvestSummaryDTO> monthlyPlantHarvestSummaryDTOArrayList = new ArrayList<>();

}
