package vn.edu.likelion.farm_management.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    Integer gender;

    @Email(message = "EMAIL_INVALID",regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    String email;

    String phone;
    String urlAvatar;
    String urlBanner;
    String local;
}
