package vn.edu.likelion.farm_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.dto.request.user.UserUpdateInfoRequest;

import vn.edu.likelion.farm_management.service.user.UserService;

@RestController
@RequestMapping(ApiPath.USER_API)
@RequiredArgsConstructor
public class UserController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;


    @GetMapping(ApiPath.FIND_ALL)
    public ResponseEntity<RestAPIResponse<Object>> findAll() {
        return responseUtil.successResponse(
                userService.findAll()
        );
    }

    @GetMapping(ApiPath.USER_API_findById)
    public ResponseEntity<RestAPIResponse<Object>> findById(@PathVariable String id) {
        return responseUtil.successResponse(
                userService.findById(id)
        );
    }
    
    @PutMapping(ApiPath.USER_API_updateInfo)
    public ResponseEntity<RestAPIResponse<Object>> updateInfo(
            @PathVariable String id, @RequestBody @Valid UserUpdateInfoRequest userUpdateInfoRequest) {
        return responseUtil.successResponse(
                userService.updateInfo(id, userUpdateInfoRequest)
        );
    }

    @PutMapping(ApiPath.USER_API_updateAvatar)
    public ResponseEntity<RestAPIResponse<Object>> updateAvatar(
            @PathVariable String id,
            @RequestParam("image") MultipartFile file) {
        return responseUtil.successResponse(
                userService.updateAvatar(id, file)
        );
    }

    @PutMapping(ApiPath.USER_API_updateBanner)
    public ResponseEntity<RestAPIResponse<Object>> updateBanner(
            @PathVariable String id,
            @RequestParam("image") MultipartFile file) {
        return responseUtil.successResponse(
                userService.updateBanner(id, file)
        );
    }
}
