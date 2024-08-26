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


    String firstName;


    String lastName;


    Integer gender;


    String email;

    String phone;
    String local;
}
