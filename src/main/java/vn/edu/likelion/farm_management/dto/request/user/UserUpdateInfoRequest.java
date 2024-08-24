package vn.edu.likelion.farm_management.dto.request.user;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateInfoRequest {

    @NotNull(message = "PARAM_NOT_NULL")
    String firstName;

    @NotNull(message = "PARAM_NOT_NULL")
    String lastName;

    @NotNull(message = "PARAM_NOT_NULL")
    Integer gender;

    @NotNull(message = "PARAM_NOT_NULL")
    String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Please enter a valid phone number")
    String phone;


    String local;
}
