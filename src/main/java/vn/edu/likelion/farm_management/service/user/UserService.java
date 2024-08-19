package vn.edu.likelion.farm_management.service.user;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.farm_management.dto.request.UserCreationRequest;
import vn.edu.likelion.farm_management.dto.request.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.UserResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import java.util.Optional;

public interface UserService extends BaseService<UserEntity, UserCreationRequest, UserResponse> {

    Optional<UserResponse> updateInfo(String id, UserUpdateInfoRequest userUpdateInfoRequest);

    Optional<UserResponse> updateAvatar(String id, MultipartFile file);


    Optional<UserResponse> updateBanner(String id,  MultipartFile file);


}
