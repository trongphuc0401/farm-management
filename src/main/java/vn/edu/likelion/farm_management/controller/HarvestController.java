package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


    @PutMapping(ApiPath.EDIT + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> update(@PathVariable(value = "id") String id,
                                                          @RequestBody HarvestCreationRequest harvestCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, harvestService.updateInfo(id, harvestCreationRequest),
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

    @GetMapping(ApiPath.FIND_ALL + ApiPath.DATE)
    public ResponseEntity<RestAPIResponse<Object>> getAllHavertByDate(
            @PathVariable(value = "date") String date,
            @RequestParam(value = "pageNo",defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10", required = false) int pageSize) {
        return responseUtil.buildResponse(RestAPIStatus.OK, harvestService.getAllHarvestByDate(date, pageNo, pageSize ), HttpStatus.OK);
    }

    @GetMapping("/getAllMoneyAndYieldGroupByDate")
    public ResponseEntity<RestAPIResponse<Object>> getAllMoneyAndYieldGroupByDate() {
        return responseUtil.buildResponse(RestAPIStatus.OK, harvestService.getAllMoneyAndYieldGroupDate(), HttpStatus.OK);
    }



}
