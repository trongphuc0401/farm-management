package vn.edu.likelion.farm_management.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.common.enums.Role;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    String firstName;
    String lastName;
    int gender;
    String email;
    String phone;
    String urlAvatar;
    String urlBanner;
    String local;
    Role role;
//    int isDeleted;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
