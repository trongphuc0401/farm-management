package vn.edu.likelion.farm_management.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


/**
 * Password -
 *
 * @param
 * @return
 * @throws
 */

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {
    String message() default "Invalid password";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
