package vn.edu.likelion.farm_management.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
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
    public UserEntity getById(String s) {
        return null;
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
