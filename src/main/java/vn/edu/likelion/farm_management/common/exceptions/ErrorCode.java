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

    USERNAME_TOO_SHORT(RestAPIStatus.BAD_REQUEST, -101, "Username must be at least 8 characters", "Bad Request", "Tên người dùng phải có ít nhất 8 ký tự"),
    PASSWORD_NO_NUMBER(RestAPIStatus.BAD_REQUEST, -102, "Password must contain at least one number", "Bad Request", "Mật khẩu phải chứa ít nhất một số"),
    EMAIL_INVALID(RestAPIStatus.BAD_REQUEST, -103, "Email is invalid", "Bad Request", "Email không hợp lệ"),
    USER_NOT_EXIST(RestAPIStatus.BAD_REQUEST, -104, "User not exist", "Bad Request", "Người dùng không tồn tại"),



    UNAUTHORIZED_ACCESS(RestAPIStatus.UNAUTHORIZED, -201, "Unauthorized access", "Unauthorized", "Truy cập không được phép"),
    FORBIDDEN(RestAPIStatus.FORBIDDEN, -202, "Forbidden: You do not have permission", "Forbidden", "Cấm: Bạn không có quyền truy cập"),
    RESOURCE_NOT_FOUND(RestAPIStatus.NOT_FOUND, -301, "Resource not found", "Not Found", "Tài nguyên không tìm thấy"),
    CONFLICT_RESOURCE_EXISTS(RestAPIStatus.CAN_NOT_DELETE, -302, "Conflict: Resource already exists", "Conflict", "Xung đột: Tài nguyên đã tồn tại"),
    UNPROCESSABLE_ENTITY(RestAPIStatus.UNPROCESSABLE, -303, "Unprocessable Entity", "Unprocessable", "Thực thể không thể xử lý"),
    INTERNAL_SERVER_ERROR(RestAPIStatus.INTERNAL_SERVER_ERROR, -401, "Internal server error", "Internal Error", "Lỗi máy chủ nội bộ"),
    NOT_IMPLEMENTED(RestAPIStatus.NOT_IMPLEMENTED, -402, "Not implemented", "Not Implemented", "Chức năng chưa được triển khai"),
    INVALID_REQUEST_PARAMETER(RestAPIStatus.BAD_REQUEST, -104, "Invalid request parameter", "Bad Request", "Tham số yêu cầu không hợp lệ"),
    MISSING_REQUIRED_FIELD(RestAPIStatus.BAD_REQUEST, -105, "Missing required field", "Bad Request", "Thiếu trường bắt buộc"),
    INVALID_CREDENTIALS(RestAPIStatus.UNAUTHORIZED, -206, "Invalid credentials", "Unauthorized", "Thông tin đăng nhập không hợp lệ"),
    ACCESS_DENIED(RestAPIStatus.FORBIDDEN, -207, "Access denied", "Forbidden", "Truy cập bị từ chối"),
    PAGE_NOT_FOUND(RestAPIStatus.NOT_FOUND, -308, "Page not found", "Not Found", "Trang không tìm thấy"),
    CONFLICT_DUPLICATE_ENTRY(RestAPIStatus.CONFLICT, -309, "Conflict: Duplicate entry", "Conflict", "Xung đột: Mục trùng lặp"),
    INVALID_INPUT_DATA(RestAPIStatus.UNPROCESSABLE, -310, "Invalid input data", "Unprocessable", "Dữ liệu đầu vào không hợp lệ"),
    DATABASE_CONNECTION_ERROR(RestAPIStatus.INTERNAL_SERVER_ERROR, -411, "Database connection error", "Internal Error", "Lỗi kết nối cơ sở dữ liệu"),
    FEATURE_NOT_AVAILABLE(RestAPIStatus.NOT_IMPLEMENTED, -412, "Feature not available", "Not Implemented", "Tính năng chưa có sẵn"),


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
