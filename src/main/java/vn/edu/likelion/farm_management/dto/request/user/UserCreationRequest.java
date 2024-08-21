package vn.edu.likelion.farm_management.dto.request.user;
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
    int gender;
    String email;
    String phone;
    String urlAvatar;
    String urlBanner;
    String local;
}
