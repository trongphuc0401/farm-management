package vn.edu.likelion.farm_management.dto.response.plant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * PlantResponse -
 *
 * @param
 * @return
 * @throws
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantResponse {

    String id;

    String name;

    String description;

    LocalDateTime datePlanted;

    Double sellingDate;

    Double vegetativeStageDate;

    Double floweringStageDate;

    Double fruitingStageDate;

    Double area;

    Double expectedYield;

    Double price;

    String typePlantId;

    String farmId;

    LocalDateTime createAt;

    LocalDateTime updateAt;
}