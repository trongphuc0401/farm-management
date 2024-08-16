package vn.edu.likelion.farm_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.service.user.UserService;

import java.util.Iterator;

@RestController
@RequestMapping(ApiPath.USER_API)
public class UserController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(ApiPath.GET_ALL)
    public ResponseEntity<RestAPIResponse<Object>> getAllUser() {
        return responseUtil.successResponse( userService.getAll());
    }

    @GetMapping("/create")
    public ResponseEntity<RestAPIResponse<Object>> create() {
        return responseUtil.successResponse( userService.getAll());
    }

    @PostMapping("/update")
    public ResponseEntity<RestAPIResponse<Object>> update() {
        return responseUtil.successResponse( userService.getAll());
    }

    @PostMapping("/delete")
    public ResponseEntity<RestAPIResponse<Object>> delete() {
        return responseUtil.successResponse( userService.getAll());
    }

}
