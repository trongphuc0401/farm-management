package vn.edu.likelion.farm_management.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String username;
    String firstName;
    String lastName;
    int gender;
    String email;
    String phone;
    String urlAvatar;
    String urlBanner;
    String local;
//    int isDeleted;
    LocalDateTime createAt;
    LocalDateTime updateAt;
}
