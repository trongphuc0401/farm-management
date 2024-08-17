package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.service.plant.PlantService;

/**
 * PlantController - 
 *
 * @param  
 * @return 
 * @throws  
 */
@RestController
@RequestMapping(ApiPath.PLANT_API)
@RequiredArgsConstructor
public class PlantController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    PlantService plantService;


    @GetMapping(ApiPath.FIND_ALL)
    public ResponseEntity<RestAPIResponse<Object>> findAll() {
        return responseUtil.successResponse(plantService.findAll());
    }

    @PostMapping(value =ApiPath.ADD ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE  )
    public ResponseEntity<RestAPIResponse<Object>> addPlant(@RequestBody PlantCreationRequest plantCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.NO_RESULT,plantService.create(plantCreationRequest),HttpStatus.CREATED);

    }





}
