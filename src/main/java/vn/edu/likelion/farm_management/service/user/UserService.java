package vn.edu.likelion.farm_management.service.user;

import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.farm_management.dto.request.user.LoginUserRequest;
import vn.edu.likelion.farm_management.dto.request.user.UserCreationRequest;
import vn.edu.likelion.farm_management.dto.request.user.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.user.LoginResponse;
import vn.edu.likelion.farm_management.dto.response.user.UserResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserService extends BaseService<UserEntity, UserCreationRequest, UserResponse, UserUpdateInfoRequest> {

    Optional<UserResponse> updateAvatar(String id, MultipartFile file);

    Optional<UserResponse> updateBanner(String id,  MultipartFile file);

    Optional<UserResponse> signup(UserCreationRequest registerUserRequest);

    Optional<LoginResponse> login(LoginUserRequest loginUserRequest);



}
