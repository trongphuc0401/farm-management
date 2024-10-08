package vn.edu.likelion.farm_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.dto.request.farm.FarmAddNewPlantRequest;
import vn.edu.likelion.farm_management.dto.request.farm.FarmAddPlantRequest;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;

import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.service.farm.FarmService;

import java.io.ByteArrayInputStream;
import java.util.List;


/**
 * FarmController -
 *
 * @param
 */

@RestController
@RequestMapping(ApiPath.FARM_API)
@RequiredArgsConstructor
public class FarmController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    FarmService farmService;


    @GetMapping(ApiPath.FIND_ALL)
    public ResponseEntity<RestAPIResponse<Object>> findAll() {
        return responseUtil.successResponse(farmService.findAll());
    }

    @PutMapping(ApiPath.EDIT + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> update(@PathVariable(value = "id") String id,
                                                          @RequestBody @Valid FarmCreationRequest farmCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.updateInfo(id, farmCreationRequest),
                HttpStatus.OK);
    }

    @GetMapping(ApiPath.FIND_BY_ID + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> findById(@PathVariable(value = "id") String id) {
        return responseUtil.successResponse(farmService.findById(id));

    }

    @DeleteMapping(value = ApiPath.DELETE + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> delete(@PathVariable(value = "id") String id) {
        farmService.delete(id);
        return responseUtil.successResponse();
    }

    @PostMapping(value = ApiPath.ADD, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> addFarm(@RequestBody @Valid FarmCreationRequest farmCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.CREATED, farmService.create(farmCreationRequest),
                HttpStatus.CREATED);
    }

    @GetMapping(ApiPath.TOTAL_PLANTED_AREA_ALL_FARM)
    public ResponseEntity<RestAPIResponse<Object>> getTotalPlantedAreaAllFarm() {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.getTotalPlantedAreaAllFarm(),
                HttpStatus.OK);
    }

    @PutMapping(value = "/addPlantToFarmByListPlantId")
    public ResponseEntity<RestAPIResponse<Object>> addPlantToFarmByListPlantId(
            @RequestBody @Valid FarmAddPlantRequest farmAddPlantRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.addPlantToFarmByListPlantId(farmAddPlantRequest),
                HttpStatus.OK);
    }

    @PostMapping(value = "/addNewPlantToFarmBaseOnQuantity")
    public ResponseEntity<RestAPIResponse<Object>> addNewPlantToFarmBaseOnQuantity(
            @RequestBody @Valid FarmAddNewPlantRequest farmAddNewPlantRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.addNewPlantToFarmBaseOnQuantity(farmAddNewPlantRequest),
                HttpStatus.OK);
    }



}
