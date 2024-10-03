package vn.edu.likelion.farm_management.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.entity.UserEntity;

/**
 * LoginResponse -
 *
 * @param
 * @return
 * @throws
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
     String token;
     UserEntity user;
     long expiresIn;
}
