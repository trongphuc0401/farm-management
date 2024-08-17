package vn.edu.likelion.farm_management.dto.request.plant;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * PlantUpdateInfoRequest -
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
public class PlantUpdateInfoRequest {
    String name;

    String description;


    Integer datePlanted;


    Integer sellingDate;


    Integer vegetativeStageDate;


    Integer floweringStageDate;


    Integer fruitingStageDate;


    Double area;


    Double expectedYield;


    Double price;
}
