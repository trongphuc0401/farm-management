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
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.service.harvest.HarvestService;

/**
 * HarvestController -
 *
 * @param
 * @return
 * @throws
 */

@RestController
@RequestMapping(ApiPath.HARVEST_API)
@RequiredArgsConstructor
public class HarvestController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    HarvestService harvestService;

    @Autowired
    private FarmRepository farmRepository;



    @PostMapping(value =ApiPath.ADD , consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> create(@RequestBody HarvestCreationRequest harvestCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.NO_RESULT,harvestService.create(harvestCreationRequest), HttpStatus.CREATED);
    }



    @PutMapping(ApiPath.EDIT + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> update(@PathVariable(value = "id") String id, @RequestBody
    HarvestCreationRequest creationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, harvestService.updateInfo(id, creationRequest),
                HttpStatus.OK);

    }

    @GetMapping(ApiPath.FIND_BY_ID + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> findById(@PathVariable(value = "id") String id) {
        return responseUtil.buildResponse(RestAPIStatus.OK, harvestService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(ApiPath.DELETE + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> delete(@PathVariable(value = "id") String id) {
        harvestService.delete(id);
        return responseUtil.successResponse();
    }

    @GetMapping(ApiPath.FIND_ALL + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> getAllHavertByDate(@PathVariable(value = "id") String id) {

        return responseUtil.successResponse();
    }


}