package vn.edu.likelion.farm_management.service.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.user.LoginUserRequest;
import vn.edu.likelion.farm_management.dto.request.user.UserCreationRequest;
import vn.edu.likelion.farm_management.dto.request.user.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.user.LoginResponse;
import vn.edu.likelion.farm_management.dto.response.user.UserResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.mapper.UserMapper;
import vn.edu.likelion.farm_management.repository.UserRepository;
import vn.edu.likelion.farm_management.security.JwtTokenProvider;
import vn.edu.likelion.farm_management.service.uploadFile.FileUpload;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FileUpload fileUpload;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired private JwtTokenProvider jwtTokenProvider;


    @Override
    public Optional<UserResponse> create(UserCreationRequest t) {
        return Optional.empty();
    }

    @Override
    public Optional<UserResponse> update(UserUpdateInfoRequest t) {
        return Optional.empty();
    }

    @Override
    public List<UserResponse> saveAll(List<UserEntity> ts) {
        return null;
    }

    @Override
    public void delete(String id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
        userEntity.setIsDeleted(1);
        userRepository.save(userEntity);
    }

    @Override
    public void deleteAll(List<String> listId) {

    }

    @Override
    public Optional<UserResponse> findById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .or(() -> {
                    throw new AppException(ErrorCode.USER_NOT_EXIST);
                });
    }

    @Override
    public List<UserResponse> findAll() {
        // Sử dụng var từ Java 10 trở lên, nó ngầm định hiểu là List<UserEntity>
        var userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new AppException(ErrorCode.USER_NOT_EXIST);
        }
        return userEntities.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> updateInfo(String id, UserUpdateInfoRequest userUpdateInfoRequest) {
        // Tìm kiếm UserEntity theo id, nếu không tìm thấy thì ném ra AppException
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));

        // Cập nhật thông tin UserEntity dựa trên thông tin từ request
        userMapper.updateUser(userEntity, userUpdateInfoRequest);

        // Lưu thay đổi và trả về UserResponse
        UserEntity updatedUser = userRepository.save(userEntity);
        UserResponse userResponse = userMapper.toUserResponse(updatedUser);

        return Optional.of(userResponse);
    }

    @Override
    public Optional<UserResponse> updateAvatar(String id, MultipartFile file) {
        try {
            // Lưu trữ file ảnh (ví dụ lưu vào thư mục local)
            String fileName = saveAvatar(file);


            String photoUrl = fileUpload.uploadFile(file);

            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
            userEntity.setUrlAvatar(photoUrl);
            UserEntity updatedUser = userRepository.save(userEntity);
            UserResponse userResponse = userMapper.toUserResponse(updatedUser);

            return Optional.of(userResponse);
        } catch (IOException e) {
            throw new AppException(ErrorCode.PHOTO_UPLOAD_FAILED);
        }
}

    @Override
    public Optional<UserResponse> updateBanner(String id, MultipartFile file) {

        try {
            // Lưu trữ file ảnh (ví dụ lưu vào thư mục local)
            String fileName = saveBanner(file);
            System.out.println("Toi da toi day");
            String photoUrl = fileUpload.uploadFile(file);

            System.out.println("Toi da gui anh xong day: " + photoUrl);
            UserEntity userEntity = userRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
            userEntity.setUrlBanner(photoUrl);
            UserEntity updatedUser = userRepository.save(userEntity);
            UserResponse userResponse = userMapper.toUserResponse(updatedUser);

            return Optional.of(userResponse);
        } catch (IOException e) {
            throw new AppException(ErrorCode.PHOTO_UPLOAD_FAILED);
        }
    }

    @Override
    public Optional<UserResponse> signup(UserCreationRequest userCreationRequest) {

        if (    userRepository.findByEmail(userCreationRequest.getEmail()).isEmpty()) {

            UserEntity userEntity = userMapper.toUser(userCreationRequest);
            userEntity.setPassword(passwordEncoder.encode(userCreationRequest.getPassword()));
            userEntity.setIsDeleted(0);
            UserEntity user = userRepository.save(userEntity);
            UserResponse userResponse = userMapper.toUserResponse(user);
            return Optional.of(userResponse);

        }else {
            throw new AppException(ErrorCode.USER_EXIST);
        }
    }

    @Override
    public Optional<LoginResponse> login(LoginUserRequest loginUserRequest) {
        UserEntity userEntity = userRepository.findByEmail(loginUserRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_OR_PASSWORD_INCORRECT));
        if (!passwordEncoder.matches(loginUserRequest.getPassword(), userEntity.getPassword())) {
            throw new AppException(ErrorCode.USER_OR_PASSWORD_INCORRECT);
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserRequest.getEmail(),
                loginUserRequest.getPassword()
        ));

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtTokenProvider.generateToken(userEntity))
                .user(userEntity)
                .expiresIn(jwtTokenProvider.getExpirationTime())
                .build();
        return Optional.of(loginResponse);
    }


    private String saveAvatar(MultipartFile file) throws IOException {
        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Đường dẫn lưu file (có thể tùy chỉnh để phù hợp với hệ thống của bạn)
        Path filePath = Paths.get("uploads/avatars", fileName);

        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(filePath.getParent());

        // Lưu file vào hệ thống tệp
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName; // Trả về tên file đã lưu
    }

    private String saveBanner(MultipartFile file) throws IOException {
        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Đường dẫn lưu file (có thể tùy chỉnh để phù hợp với hệ thống của bạn)
        Path filePath = Paths.get("uploads/banners", fileName);

        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(filePath.getParent());

        // Lưu file vào hệ thống tệp
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return fileName; // Trả về tên file đã lưu
    }
}
