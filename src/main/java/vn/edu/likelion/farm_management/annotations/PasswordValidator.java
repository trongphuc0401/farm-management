package vn.edu.likelion.farm_management.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.LengthRule;

import java.util.Arrays;
import java.util.List;
import org.passay.*;
/**
 * PasswordValidator -
 *
 * @param
 * @return
 * @throws
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    public <T> PasswordValidator(List<T> list) {
    }


    @Override public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        org.passay.PasswordValidator passwordValidator = new org.passay.PasswordValidator(
                Arrays.asList(
                        new LengthRule(8,20),
                        new CharacterRule(EnglishCharacterData.UpperCase,1),
                        new CharacterRule(EnglishCharacterData.LowerCase,1),
                        new CharacterRule(EnglishCharacterData.Digit,1),
                        new CharacterRule(EnglishCharacterData.Special,1),
                        new WhitespaceRule()
                )
        );
        RuleResult result = passwordValidator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        constraintValidatorContext.buildConstraintViolationWithTemplate(
                        passwordValidator
                                .getMessages(result).stream().findFirst().get())
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}
