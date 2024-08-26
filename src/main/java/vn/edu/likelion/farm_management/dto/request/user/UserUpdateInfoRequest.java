package vn.edu.likelion.farm_management.dto.request.user;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.edu.likelion.farm_management.annotations.Gender;
import vn.edu.likelion.farm_management.annotations.Phone;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateInfoRequest {


    String firstName;
    String lastName;

    @Gender(message = "PARAM_INVALID")
    Integer gender;
    @Email(message = "EMAIL_INVALID")
    String email;
    @Phone(message = "PARAM_INVALID")
    String phone;
    String local;
}
