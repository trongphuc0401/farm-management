package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import vn.edu.likelion.farm_management.dto.request.UserCreationRequest;
import vn.edu.likelion.farm_management.dto.request.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.UserResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity toUser(UserCreationRequest userCreationRequest);


    UserResponse toUserResponse(UserEntity userEntity);

    void updateUser(@MappingTarget UserEntity user, UserUpdateInfoRequest userUpdateInfoRequest);

}