package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.service.farm.FarmService;
import vn.edu.likelion.farm_management.service.plant.PlantService;

/**
 * FarmController -
 *
 * @param
 * @return
 * @throws
 */

@RestController
@RequestMapping(ApiPath.FARM_API)
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    FarmService farmService;

    @PostMapping(value = ApiPath.ADD, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> addFarm(@RequestBody FarmCreationRequest farmCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.NO_RESULT,farmService.create(farmCreationRequest), HttpStatus.CREATED);
    }

}
