package vn.edu.likelion.farm_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.edu.likelion.farm_management.dto.request.UserCreationRequest;
import vn.edu.likelion.farm_management.dto.request.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.UserResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toUser(UserCreationRequest userCreationRequest);

    UserResponse toUserResponse(UserEntity userEntity);

//    @Mapping(target = "password", ignore = true)
    void updateUser(@MappingTarget UserEntity user, UserUpdateInfoRequest userUpdateInfoRequest);

}