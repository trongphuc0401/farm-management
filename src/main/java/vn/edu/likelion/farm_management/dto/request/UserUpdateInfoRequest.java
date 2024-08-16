package vn.edu.likelion.farm_management.dto.request;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateInfoRequest {
    String firstName;
    String lastName;
    int gender;
    String email;
    String phone;
    String local;
}
