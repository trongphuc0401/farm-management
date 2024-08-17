package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;

import java.util.Optional;

/**
 * PlantMapper -
 *
 * @param
 * @return
 * @throws
 */
@Mapper(componentModel = "spring")
public interface PlantMapper {
    PlantMapper INSTANCE = Mappers.getMapper(PlantMapper.class);

    PlantEntity toPlant(PlantCreationRequest plantCreationRequest);

    PlantResponse toPlantResponse(PlantEntity plantEntity);

    // void updatPlantEntity(PlantEntity plantEntity, PlantCreationRequest plantCreationRequest);

}