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
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
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

    @GetMapping(ApiPath.TYPE_PLANT)
    public ResponseEntity<RestAPIResponse<Object>> findAllTypePlant() {
        return responseUtil.successResponse(plantService.findAllTypePlant());
    }

    @GetMapping(ApiPath.FIND_ALL+ ApiPath.PAGINATE)

    public ResponseEntity<RestAPIResponse<Object>> findAllByPagination(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return responseUtil.successResponse(plantService.getAllByPagination(pageNo, pageSize));
    }


    @PostMapping(value = ApiPath.ADD, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> addPlant(@RequestBody PlantCreationRequest plantCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.NO_RESULT, plantService.create(plantCreationRequest),
                HttpStatus.CREATED);

    }

    @PutMapping(value = ApiPath.EDIT + ApiPath.ID, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> updatePlant(@PathVariable String id, @RequestBody
    PlantUpdateInfoRequest plantUpdateInfoRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, plantService.updateInfo(id, plantUpdateInfoRequest),
                HttpStatus.OK);

    }

    @PutMapping(value = ApiPath.ADD_TO_FARM,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> addPlantToFarm(
            @RequestParam String plantId,
            @RequestParam String farmId ) {

        return responseUtil.buildResponse(RestAPIStatus.OK,plantService.addPlantToFarm(plantId,farmId),HttpStatus.OK);

    }

    @DeleteMapping(value = ApiPath.DELETE + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> deletePlant(@PathVariable String id) {
        plantService.delete(id);
        return responseUtil.successResponse();
    }

    @GetMapping(ApiPath.FIND_BY_ID + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> findById(@PathVariable(value = "id") String id) {
        return responseUtil.successResponse(plantService.findById(id));
    }

    @GetMapping(ApiPath.SEARCH)
    public ResponseEntity<RestAPIResponse<Object>> searchPlants(
            @RequestParam String searchText,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return responseUtil.successResponse(plantService.searchPlantsByPagination(searchText,pageNo, pageSize));
    }


}
