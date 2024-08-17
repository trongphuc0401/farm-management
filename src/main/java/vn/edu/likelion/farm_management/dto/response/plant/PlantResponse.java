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

    LocalDateTime createAt;

    LocalDateTime updateAt;
}
