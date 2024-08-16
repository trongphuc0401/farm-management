package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.mapper.UserMapper;
import vn.edu.likelion.farm_management.service.user.UserService;

@RestController
@RequestMapping(ApiPath.USER_API)
@RequiredArgsConstructor
public class UserController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping(ApiPath.GET_ALL)
    public ResponseEntity<RestAPIResponse<Object>> getAllUser() {
        return responseUtil.successResponse(
                userService.getAll().stream().map(userMapper::toUserResponse).toList()
        );
    }


}
