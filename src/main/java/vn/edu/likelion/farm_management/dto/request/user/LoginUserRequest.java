package vn.edu.likelion.farm_management.dto.request.user;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.annotations.Password;

/**
 * LoginUserRequest -
 *
 * @param
 * @return
 * @throws
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginUserRequest {

    @Email(message = "EMAIL_INVALID")
    String email;

    @Password()
    String password;
}
