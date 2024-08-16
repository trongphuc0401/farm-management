package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.dto.request.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.mapper.UserMapper;
import vn.edu.likelion.farm_management.service.user.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

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
            @PathVariable String id, @RequestBody UserUpdateInfoRequest userUpdateInfoRequest) {
        return responseUtil.successResponse(
                userService.updateInfo(id, userUpdateInfoRequest)
        );
    }

    @PostMapping(ApiPath.USER_API_updateAvatar)
    public ResponseEntity<RestAPIResponse<Object>> updateAvatar(
            @PathVariable String id,
            @RequestParam("image") MultipartFile file) {
        return responseUtil.successResponse(
                userService.updateAvatar(id, file)
        );
    }

    @PostMapping(ApiPath.USER_API_updateBanner)
    public ResponseEntity<RestAPIResponse<Object>> updateBanner(
            @PathVariable String id,
            @RequestParam("image") MultipartFile file) {
        return responseUtil.successResponse(
                userService.updateBanner(id, file)
        );
    }
}
