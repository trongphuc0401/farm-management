package vn.edu.likelion.farm_management.service.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.mapper.UserMapper;
import vn.edu.likelion.farm_management.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public Optional<UserEntity> save(UserEntity userEntity) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> saveAll(List<UserEntity> userEntities) {
        return List.of();
    }

    @Override
    public void delete(UserEntity userEntity) {

    }

    @Override
    public void deleteAll(List<UserEntity> userEntities) {

    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        ));
    }

    @Override
    public List<UserEntity> getAll() {
        return Optional.of(userRepository.findAll())
                .filter(users -> !users.isEmpty())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXIST));
    }

}
