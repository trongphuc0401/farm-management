package vn.edu.likelion.farm_management.dto.request.plant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * PlantUpdateToFarmRequest -
 *
 * @param
 * @return
 * @throws
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantUpdateToFarmRequest {

    String farmId;

    Integer status;

    String statusName;

    LocalDateTime dateSeedlingFinish;

    LocalDateTime dateVegetativeStageFinish;

    LocalDateTime dateFloweringStageFinish;

    LocalDateTime dateFruitingStageFinish;

    LocalDateTime updateAt;





}
