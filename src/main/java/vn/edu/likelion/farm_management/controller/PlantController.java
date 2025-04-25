package vn.edu.likelion.farm_management.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Slf4j
public class PlantController {

    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    PlantService plantService;

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.FIND_ALL)
    public ResponseEntity<RestAPIResponse<Object>> findAll() {
        return responseUtil.successResponse(plantService.findAll());
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.TYPE_PLANT)
    public ResponseEntity<RestAPIResponse<Object>> findAllTypePlant() {
        return responseUtil.successResponse(plantService.findAllTypePlant());
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.FIND_ALL_PLANT_BY_FARM + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> findAllPlantByFarm(
            @PathVariable String id
    ) {
        return responseUtil.successResponse(plantService.findAllPlantByFarm(id));
    }


    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.FIND_ALL + ApiPath.PAGINATE)
    public ResponseEntity<RestAPIResponse<Object>> findAllByPagination(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return responseUtil.successResponse(plantService.getAllByPagination(pageNo, pageSize));
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping("/findAllByTypePlantId")
    public ResponseEntity<RestAPIResponse<Object>> findAllByTypePlantId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "typePlantId", required = true) String typePlantId
            ) {
        return responseUtil.successResponse(plantService.findAllByTypePlantId(pageNo, pageSize, typePlantId));
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @PostMapping(value = ApiPath.ADD, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> addPlant(
            @RequestBody @Valid PlantCreationRequest plantCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.CREATED, plantService.create(plantCreationRequest),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @PostMapping(value = ApiPath.ADD + "ByQuantity")
    public ResponseEntity<RestAPIResponse<Object>> addPlantBaseOnQuantity(
            @RequestParam(value = "quantity") int quantity,
            @RequestBody @Valid PlantCreationRequest plantCreationRequest) {
        return responseUtil.buildResponse(RestAPIStatus.CREATED,
                plantService.addPlantBaseOnQuantity(quantity, plantCreationRequest),
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @PutMapping(value = ApiPath.EDIT + ApiPath.ID, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestAPIResponse<Object>> updatePlant(@PathVariable String id, @RequestBody @Valid
    PlantUpdateInfoRequest plantUpdateInfoRequest) {
        return responseUtil.buildResponse(RestAPIStatus.OK, plantService.updateInfo(id, plantUpdateInfoRequest),
                HttpStatus.OK);

    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @PutMapping(value = ApiPath.ADD_TO_FARM)
    public ResponseEntity<RestAPIResponse<Object>> addPlantToFarm(
            @RequestParam String plantId,
            @RequestParam String farmId) {
        return responseUtil.buildResponse(RestAPIStatus.OK, plantService.addPlantToFarm(plantId, farmId),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @DeleteMapping(value = ApiPath.DELETE + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> deletePlant(@PathVariable String id) {
        plantService.delete(id);
        return responseUtil.successResponse();
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.FIND_BY_ID + ApiPath.ID)
    public ResponseEntity<RestAPIResponse<Object>> findById(@PathVariable(value = "id") String id) {
        return responseUtil.successResponse(plantService.findById(id));
    }

    @PreAuthorize("hasAuthority(\"ROLE_USER\")")
    @GetMapping(ApiPath.SEARCH)
    public ResponseEntity<RestAPIResponse<Object>> searchPlants(
            @RequestParam String searchText,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return responseUtil.successResponse(plantService.searchPlantsByPagination(searchText, pageNo, pageSize));
    }
}
