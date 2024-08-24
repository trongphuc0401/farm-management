package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import vn.edu.likelion.farm_management.common.enums.StatusFarm;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;

/**
 * FarmMapper -
 *
 * @param
 * @return
 * @throws
 */
@Mapper(componentModel = "spring")
public interface FarmMapper {
    FarmMapper INSTANCE = Mappers.getMapper(FarmMapper.class);

    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    FarmEntity toCreateFarm(FarmCreationRequest farmCreationRequest);

    @Mapping(target = "status", source = "status", qualifiedByName = "statusToString")
    FarmGeneralResponse toFarmGeneralResponse(FarmEntity farmEntity);

    void updateEntity(@MappingTarget FarmEntity farmEntity, FarmCreationRequest farmCreationRequest);

    @Named("stringToStatus")
    default StatusFarm stringToStatus(String status) {
        return StatusFarm.valueOf(status.toUpperCase());
    }

    @Named("statusToString")
    default String statusToString(StatusFarm status) {
        return status.getDisplayName();
    }
}

