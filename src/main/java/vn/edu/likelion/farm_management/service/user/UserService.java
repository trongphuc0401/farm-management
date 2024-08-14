package vn.edu.likelion.farm_management.service.user;

import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.service.BaseService;

public interface UserService extends BaseService<UserEntity,String> {
    UserEntity findOrCreateUser(String email, String username ,String googleId);

}
