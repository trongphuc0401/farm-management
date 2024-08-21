package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

}
