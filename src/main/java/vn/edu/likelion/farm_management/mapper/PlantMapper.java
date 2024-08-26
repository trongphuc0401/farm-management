package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.cglib.core.Local;
import vn.edu.likelion.farm_management.common.enums.StatusPlant;
import vn.edu.likelion.farm_management.common.utils.PlantLifecycleUtils;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateToFarmRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PaginatePlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.TypePlantResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.entity.TypePlantEntity;
import vn.edu.likelion.farm_management.repository.FarmRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * PlantMapper -
 *
 * @param
 * @return
 * @throws
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PlantMapper {
    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    @Mapping(source = "typePlantId", target = "typePlant.id")
    PlantEntity toCreatePlant(PlantCreationRequest plantCreationRequest);

//    @Mapping(source = "typePlantId", target = "typePlant.id")
    PlantEntity toUpdatePlant(PlantUpdateInfoRequest plantUpdateInfoRequest);

    @Mapping(target = "status", source = ".", qualifiedByName = "calculateStatus")
    @Mapping(target = "statusName", source = ".", qualifiedByName = "calculateStatusName")
    @Mapping(source = "typePlant.id", target = "typePlantId")
    PlantResponse toPlantResponse(PlantEntity plantEntity);

    TypePlantResponse toTypePlantResponse(TypePlantEntity typePlantEntity);

    PaginatePlantResponse toPaginatePlantResponse(PlantEntity plantEntity);

//    @Mapping(source = "typePlantId", target = "typePlant.id")
    void updatePlantEntity(@MappingTarget PlantEntity plantEntity, PlantUpdateInfoRequest plantUpdateInfoRequest );

    static PlantEntity toUpdateToFarmPlant(PlantEntity plantEntity,String farmId){

        LocalDateTime datePlanted = LocalDateTime.now();
        plantEntity.setDatePlanted(datePlanted);
        plantEntity.setFarmId(farmId);
        plantEntity.setDateSeedlingFinish(PlantLifecycleUtils.calculateSeedlingDate(plantEntity.getSeedlingDay(),datePlanted));
        plantEntity.setDateVegetativeStageFinish(PlantLifecycleUtils.calculateVegetativeDate(plantEntity.getVegetativeStageDay(),plantEntity.getDateSeedlingFinish()));
        plantEntity.setDateFloweringStageFinish(PlantLifecycleUtils.calculateFloweringDate(plantEntity.getFloweringStageDay(),plantEntity.getDateVegetativeStageFinish()));
        plantEntity.setDateFruitingStageFinish(PlantLifecycleUtils.calculateFruitingDate(plantEntity.getFruitingStageDay(),plantEntity.getDateFloweringStageFinish()));
        plantEntity.setUpdateAt(LocalDateTime.now());


        return plantEntity;

    }

    @Named("calculateStatus")
    default int calculateStatus(PlantEntity plant) {
        return getStatusPlant(plant).getCode();
    }

    @Named("calculateStatusName")
    default String calculateStatusName(PlantEntity plant) {
        return getStatusPlant(plant).getDisplayName();
    }

    @Named("getStatusPlant")
    default StatusPlant getStatusPlant(PlantEntity plant) {
        LocalDateTime now = LocalDateTime.now();

        if (plant.getDateFruitingStageFinish() != null && !now.isBefore(plant.getDateFruitingStageFinish())) {
            return StatusPlant.HARVESTED;
        } else if (plant.getDateFloweringStageFinish() != null && !now.isBefore(plant.getDateFloweringStageFinish())) {
            return StatusPlant.FRUITING;
        } else if (plant.getDateVegetativeStageFinish() != null && !now.isBefore(plant.getDateVegetativeStageFinish())) {
            return StatusPlant.FLOWERING;
        } else if (plant.getDateSeedlingFinish() != null && !now.isBefore(plant.getDateSeedlingFinish())) {
            return StatusPlant.VEGETATIVE;
        } else if (plant.getDatePlanted() != null && !now.isBefore(plant.getDatePlanted())) {
            return StatusPlant.SEEDLING;
        } else {
            return StatusPlant.UNPLANNED;
        }
    }


}