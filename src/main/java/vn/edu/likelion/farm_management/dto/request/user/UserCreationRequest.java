package vn.edu.likelion.farm_management.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.annotations.Gender;
import vn.edu.likelion.farm_management.annotations.Password;
import vn.edu.likelion.farm_management.annotations.Phone;
import vn.edu.likelion.farm_management.common.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    @Password()
    String password;
    String firstName;
    String lastName;

    Integer gender;

    @Email(message = "EMAIL_INVALID")
    String email;

    Role role;
    String phone;
    String urlAvatar;
    String urlBanner;
    String local;

}
