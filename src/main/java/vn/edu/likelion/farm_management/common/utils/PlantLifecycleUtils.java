package vn.edu.likelion.farm_management.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * PlantLifecycleUtils -
 *
 * @param
 * @return
 * @throws
 */
public class PlantLifecycleUtils {

    public static LocalDateTime calculateSeedlingDate(Integer seedlingDay , LocalDateTime datePlanted ) {

       LocalDateTime date = LocalDateTime.of(datePlanted.getYear(),datePlanted.getMonth(), datePlanted.getDayOfMonth(), datePlanted.getHour(),datePlanted.getMinute());
       date = date.plusDays(seedlingDay);

       return date;

    }


    public static LocalDateTime calculateVegetativeDate(Integer vegetativeStageDay, LocalDateTime dateSeedlingFinish) {
        return calculateSeedlingDate(vegetativeStageDay, dateSeedlingFinish);
    }

    public static LocalDateTime calculateFloweringDate(Integer floweringStageDay, LocalDateTime dateVegetativeStageFinish) {
        return calculateSeedlingDate(floweringStageDay, dateVegetativeStageFinish);
    }

    public static LocalDateTime calculateFruitingDate(Integer fruitingStageDay, LocalDateTime dateFruitingStageFinish) {
        return calculateSeedlingDate(fruitingStageDay, dateFruitingStageFinish);
    }
}
