package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
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

    FarmEntity toCreateFarm(FarmCreationRequest farmCreationRequest);



    FarmGeneralResponse toFarmGeneralResponse(FarmEntity farmEntity);



}
