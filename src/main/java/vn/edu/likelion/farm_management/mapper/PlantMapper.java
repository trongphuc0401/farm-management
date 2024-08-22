package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PaginatePlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.TypePlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.entity.TypePlantEntity;

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

    PlantEntity toCreatePlant(PlantCreationRequest plantCreationRequest);


    PlantEntity toUpdatePlant(PlantUpdateInfoRequest plantUpdateInfoRequest);


    PlantResponse toPlantResponse(PlantEntity plantEntity);

    TypePlantResponse toTypePlantResponse(TypePlantEntity typePlantEntity);

    PaginatePlantResponse toPaginatePlantResponse(PlantEntity plantEntity);

    void updatePlantEntity(@MappingTarget PlantEntity plantEntity, PlantUpdateInfoRequest plantUpdateInfoRequest );

}