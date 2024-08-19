package vn.edu.likelion.farm_management.dto.request.plant;


import lombok.*;
import lombok.experimental.FieldDefaults;


/**
 * PlantCreationRequest -
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
public class PlantCreationRequest {

    String name;

    String description;

    Double sellingDate;

    Double vegetativeStageDate;

    Double floweringStageDate;

    Double fruitingStageDate;

    Double area;

    Double expectedYield;

    Double price;

    String typePlantId;

}