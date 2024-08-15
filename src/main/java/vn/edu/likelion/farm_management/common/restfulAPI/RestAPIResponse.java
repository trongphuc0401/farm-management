package vn.edu.likelion.farm_management.common.restfulAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class RestAPIResponse <T extends Object>  implements Serializable {
    private int status;
    private int code;
    private String message;
    private  T data;
    private LocalDateTime timestamp;

    public RestAPIResponse(RestAPIStatus restApiStatus, T data) {

        if (restApiStatus == null) {
            throw new IllegalArgumentException("APIStatus must not be null");
        }

        this.status =restApiStatus.getCode();
        this.code = restApiStatus.getCode();
        this.message = restApiStatus.getDescription();
        this.data = data;

    }
    public RestAPIResponse(RestAPIStatus restApiStatus, T data, String description) {

        if (restApiStatus == null) {
            throw new IllegalArgumentException("APIStatus must not be null");
        }

        this.code = restApiStatus.getCode();
        this.message = restApiStatus.getDescription();
        this.data = data;
        this.description = description;
    }
}
