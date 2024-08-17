package vn.edu.likelion.farm_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.mapper.UserMapper;
import vn.edu.likelion.farm_management.service.plant.PlantService;
import vn.edu.likelion.farm_management.service.user.UserService;

import java.util.Optional;

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

    @Autowired
    PlantMapper plantMapper;

    @PostMapping(ApiPath.ADD)
    public ResponseEntity<RestAPIResponse<Object>> addPlant(@RequestBody PlantCreationRequest plantCreationRequest) {
        PlantEntity plantEntity = plantMapper.toPlant(plantCreationRequest);

        // Lưu PlantEntity vào cơ sở dữ liệu
        Optional<PlantEntity> savedPlantEntity = plantService.save(plantEntity);

        // Chuyển đổi từ PlantEntity đã lưu sang PlantResponse
        PlantResponse plantResponse = plantMapper.toPlantResponse(savedPlantEntity);

        // Trả về phản hồi thành công
        return responseUtil.successResponse(plantResponse);
    }

}
