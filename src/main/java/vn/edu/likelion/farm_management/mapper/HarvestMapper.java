package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.entity.HarvestEntity;

/**
 * HarvestMapper -
 *
 * @param
 * @return
 * @throws
 */
@Mapper(componentModel = "spring")
public interface HarvestMapper {
    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    HarvestEntity toCreateHarvest(HarvestCreationRequest harvestCreationRequest);

    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "updateAt", target = "updateAt")
    HarvestResponse toHarvestResponse(HarvestEntity harvestEntity);

    void updateEntity(@MappingTarget HarvestEntity harvestEntity, HarvestCreationRequest harvestCreationRequest);

}
