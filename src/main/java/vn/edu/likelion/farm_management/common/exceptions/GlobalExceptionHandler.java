package vn.edu.likelion.farm_management.common.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;

import java.time.LocalDateTime;

/**
 * GlobalExceptionHandler -
 *
 * @param
 * @return
 * @throws
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity handlingRuntimeException(RuntimeException exception) {
        RestAPIResponse restAPIResponse = new RestAPIResponse();

        restAPIResponse.setStatus(ErrorCode.UNCATEGORIZED_EXCEPTION.getStatusCode());
        restAPIResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCodeError());
        restAPIResponse.setMessageEng(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessageEng());
        restAPIResponse.setMessageVN(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessageVN());

        restAPIResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(restAPIResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        RestAPIResponse restAPIResponse = new RestAPIResponse();

        restAPIResponse.setStatus(errorCode.getStatusCode());
        restAPIResponse.setCode(errorCode.getCodeError());
        restAPIResponse.setMessageEng(errorCode.getMessageEng());
        restAPIResponse.setMessageVN(errorCode.getMessageVN());
        restAPIResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(restAPIResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity handlingValidation(MethodArgumentNotValidException exception) {

        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e) {

        }

        RestAPIResponse restAPIResponse = new RestAPIResponse();

        restAPIResponse.setStatus(errorCode.getStatusCode());
        restAPIResponse.setCode(errorCode.getCodeError());
        restAPIResponse.setMessageEng(errorCode.getMessageEng());
        restAPIResponse.setMessageVN(errorCode.getMessageVN());
        restAPIResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity.badRequest().body(restAPIResponse);
    }




}
