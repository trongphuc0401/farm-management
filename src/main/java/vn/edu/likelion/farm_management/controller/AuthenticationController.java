package vn.edu.likelion.farm_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.dto.request.user.LoginUserRequest;
import vn.edu.likelion.farm_management.dto.request.user.UserCreationRequest;
import vn.edu.likelion.farm_management.service.user.UserService;

/**
 * AuthenticationController -
 *
 * @param
 * @return
 * @throws
 */
@RestController
@RequestMapping(ApiPath.AUTHENTICATE)
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;

    @PostMapping(ApiPath.SIGN_UP)

    public ResponseEntity<RestAPIResponse<Object>> signup(@Valid @RequestBody UserCreationRequest userCreationRequest) {
        return responseUtil.successResponse(userService.signup(userCreationRequest));
    }

    @PostMapping(ApiPath.LOGIN)
    public ResponseEntity<RestAPIResponse<Object>> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        return responseUtil.successResponse(userService.login(loginUserRequest));
    }
}
