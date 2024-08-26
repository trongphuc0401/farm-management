package vn.edu.likelion.farm_management.annotations;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final String PHONE_REGEX = "^\\d{10}$"; // Ví dụ: kiểm tra số điện thoại phải có 10 chữ số

    @Override
    public void initialize(Phone constraintAnnotation) {
        // Khởi tạo nếu cần
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return false;
        }
        return phone.matches(PHONE_REGEX);
    }
}
