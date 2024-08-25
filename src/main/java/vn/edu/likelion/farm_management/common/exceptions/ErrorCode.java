package vn.edu.likelion.farm_management.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;

import java.time.LocalDateTime;

/**
 * ErrorCode -
 *
 * @param
 * @return
 * @throws
 */
@Getter
public enum ErrorCode {

    OK(RestAPIStatus.OK,1200,"Success","OK","Thành công"),

    INVALID_KEY(RestAPIStatus.BAD_REQUEST,-100,"Invalid message key","Bad Request","Message key không hợp lệ"),

    USERNAME_TOO_SHORT(RestAPIStatus.BAD_REQUEST, -101, "Username must be at least {min} characters", "Bad Request", "Tên người dùng phải có ít nhất {min} ký tự"),
    PASSWORD_NO_NUMBER(RestAPIStatus.BAD_REQUEST, -102, "Password must contain at least one number", "Bad Request", "Mật khẩu phải chứa ít nhất một số"),
    EMAIL_INVALID(RestAPIStatus.BAD_REQUEST, -103, "Email is invalid", "Bad Request", "Email không hợp lệ"),
    USER_NOT_EXIST(RestAPIStatus.BAD_REQUEST, -104, "User not exist", "Bad Request", "Người dùng không tồn tại"),
    MISSING_REQUIRED_FIELD(RestAPIStatus.BAD_REQUEST, -105, "Missing required field", "Bad Request", "Thiếu trường bắt buộc"),
    PHOTO_UPLOAD_FAILED(RestAPIStatus.BAD_REQUEST, -106, "Failed to save photo", "Bad Request", "Upload ảnh thất bại"),
    INVALID_DATE_FORMAT(RestAPIStatus.BAD_REQUEST, -107, "Invalid date format", "Bad Request", "Định dạng ngày không hợp lệ"),


    PLANT_EXIST(RestAPIStatus.EXISTED , -110,"Plant Exist","Already Exist","Cây trồng đã tồn tại"),
    PLANT_NOT_EXIST(RestAPIStatus.NOT_FOUND , -111,"Plant Not Exist","Not Found","Cây trồng không được tìm thấy"),
    TYPE_PLANT_NOT_EXIST(RestAPIStatus.NOT_FOUND , -112,"Type Plant Not Exist","Not Found","Giống cây trồng không được tìm thấy"),
    NO_PLANTS_READY_TO_HARVEST(RestAPIStatus.NOT_FOUND,-113,"No Plants ready to Harvest ","Not found","Không cây nào sẳn sàng để thu hoạch"),


    FARM_EXIST(RestAPIStatus.EXISTED,-120,"Farm Exist","Already Exist","Nông trại đã tồn tại"),
    FARM_NOT_EXIST(RestAPIStatus.NOT_FOUND,-121,"Farm not exist","Not found","Nông trại không tồn tại"),
    FARM_FULL(RestAPIStatus.BAD_REQUEST,-122,"Farm full","Bad request","Nông trại hết chỗ"),
    FARM_UPDATE_AREA_FAIL(RestAPIStatus.BAD_REQUEST,-123,"Area Farm is not update","Bad request","Không thể cập nhật diện tích nông trại!"),


    HARVEST_NOT_EXIST(RestAPIStatus.NOT_FOUND,-130,"Harvest not exist","Not found","Không tìm thấy bản ghi thu hoạch cây trồng nào!"),

    UNAUTHORIZED_ACCESS(RestAPIStatus.UNAUTHORIZED, -201, "Unauthorized access", "Unauthorized", "Truy cập không được phép"),
    FORBIDDEN(RestAPIStatus.FORBIDDEN, -202, "Forbidden: You do not have permission", "Forbidden", "Cấm: Bạn không có quyền truy cập"),
    RESOURCE_NOT_FOUND(RestAPIStatus.NOT_FOUND, -301, "Resource not found", "Not Found", "Tài nguyên không tìm thấy"),
    CONFLICT_RESOURCE_EXISTS(RestAPIStatus.CAN_NOT_DELETE, -302, "Conflict: Resource already exists", "Conflict", "Xung đột: Tài nguyên đã tồn tại"),
    UNPROCESSABLE_ENTITY(RestAPIStatus.UNPROCESSABLE, -303, "Unprocessable Entity", "Unprocessable", "Thực thể không thể xử lý"),
    INTERNAL_SERVER_ERROR(RestAPIStatus.INTERNAL_SERVER_ERROR, -401, "Internal server error", "Internal Error", "Lỗi máy chủ nội bộ"),
    NOT_IMPLEMENTED(RestAPIStatus.NOT_IMPLEMENTED, -402, "Not implemented", "Not Implemented", "Chức năng chưa được triển khai"),

    INVALID_CREDENTIALS(RestAPIStatus.UNAUTHORIZED, -206, "Invalid credentials", "Unauthorized", "Thông tin đăng nhập không hợp lệ"),
    ACCESS_DENIED(RestAPIStatus.FORBIDDEN, -207, "Access denied", "Forbidden", "Truy cập bị từ chối"),
    PAGE_NOT_FOUND(RestAPIStatus.NOT_FOUND, -308, "Page not found", "Not Found", "Trang không tìm thấy"),
    CONFLICT_DUPLICATE_ENTRY(RestAPIStatus.CONFLICT, -309, "Conflict: Duplicate entry", "Conflict", "Xung đột: Mục trùng lặp"),
    INVALID_INPUT_DATA(RestAPIStatus.UNPROCESSABLE, -310, "Invalid input data", "Unprocessable", "Dữ liệu đầu vào không hợp lệ"),
    DATABASE_CONNECTION_ERROR(RestAPIStatus.INTERNAL_SERVER_ERROR, -411, "Database connection error", "Internal Error", "Lỗi kết nối cơ sở dữ liệu"),
    FEATURE_NOT_AVAILABLE(RestAPIStatus.NOT_IMPLEMENTED, -412, "Feature not available", "Not Implemented", "Tính năng chưa có sẵn"),
    RESPONSE_DTO_CONVERSION_FAILED(RestAPIStatus.INTERNAL_SERVER_ERROR, -414, "Failed to convert to ResponseDTO", "Internal Server Error", "Không thể chuyển đổi sang ResponseDTO"),


    INVALID_REQUEST_PARAMETER(RestAPIStatus.BAD_REQUEST, -600, "Invalid request parameter", "Bad Request", "Tham số yêu cầu không hợp lệ"),
    AREA_NEGATIVE(RestAPIStatus.BAD_REQUEST, -601, "Area cannot be negative", "Bad request", "Diện tích không được âm"),
    AREA_TOO_LARGE(RestAPIStatus.BAD_REQUEST, -602, "Area  exceeds maximum allowed value {value} - {fieldName}", "Bad request", "Diện tích vượt quá giá trị cho phép {value} - {fieldName}"),
    PARAM_NOT_NULL(RestAPIStatus.BAD_REQUEST, -603, "{fieldName} cannot be null", "Bad request", "Giá trị {fieldName} không được null"),
    PARAM_OVER_MIN (RestAPIStatus.BAD_REQUEST, -604, "{fieldName} must be greater than or equal {value}", "Invalid Field", "Giá trị {fieldName} phải lớn hơn hoặc bằng {value}"),
    PARAM_OVER_MAX(RestAPIStatus.BAD_REQUEST, -605, "{fieldName} must be less than or equal {value}", "Invalid Field", "Giá trị {fieldName} bé hơn hoặc bằng {value}"),
    PARAM_NEGATIVE(RestAPIStatus.BAD_REQUEST, -606, "{fieldName} cannot be negative", "Bad request", "{fieldName} không được âm"),


    DELETE_FAILED(RestAPIStatus.CAN_NOT_DELETE,-9996,"Delete failed","Not found","Xoá thất bại"),

    UPDATE_FAILED(RestAPIStatus.BAD_REQUEST,-9997,"Update failed","Not found","Cập nhật thất bại"),
    QUERY_NOT_FOUND(RestAPIStatus.BAD_REQUEST,-9998,"Query not found","Not found","Truy vấn không thành công"),
    UNCATEGORIZED_EXCEPTION(RestAPIStatus.BAD_REQUEST,-9999,"Uncategorized error","Bad request","Lỗi chưa được định nghĩa");

    private final int statusCode;
    private final int codeError;
    private final String messageEng;
    private final String httpStatus;
    private final String messageVN;

    ErrorCode(RestAPIStatus restApiStatus ,int errorCode, String messageEng, String httpStatus, String messageVN) {
        this.statusCode = restApiStatus.getCode();
        this.codeError = errorCode;
        this.messageEng = messageEng;
        this.httpStatus = httpStatus;
        this.messageVN = messageVN;
    }

}
