package vn.edu.likelion.farm_management.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.entity.UserEntity;
import vn.edu.likelion.farm_management.dto.request.TokenRequest;
import vn.edu.likelion.farm_management.dto.response.AuthResponse;
import vn.edu.likelion.farm_management.service.user.UserService;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    ResponseUtil responseUtil;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<RestAPIResponse<Object>> getAllUser() {
        return responseUtil.successResponse( userService.getAll());
    }

    @PostMapping("/google-login")
    public ResponseEntity<RestAPIResponse<Object>> googleLogin(@RequestBody TokenRequest tokenRequest) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();
            GoogleIdToken idToken = verifier.verify(tokenRequest.getToken());
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String googleId = payload.getSubject();
                String email = payload.getEmail();
                String username = (String) payload.get("username");

                UserEntity userEntity = userService.findOrCreateUser(email,username,googleId);

                String jwtToken = "id: " + userEntity.getId();
               return responseUtil.buildResponse( RestAPIStatus.NO_RESULT,new AuthResponse(jwtToken),HttpStatus.CREATED);
            } else {
                return responseUtil.buildResponse(RestAPIStatus.BAD_REQUEST,null,"Invalid ID token",HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            return responseUtil.buildResponse(RestAPIStatus.INTERNAL_SERVER_ERROR,null,e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
