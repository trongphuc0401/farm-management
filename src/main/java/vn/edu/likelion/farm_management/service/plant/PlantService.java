package vn.edu.likelion.farm_management.service.plant;

import vn.edu.likelion.farm_management.dto.request.UserUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.UserResponse;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import java.util.Optional;

/**
 * PlantService -
 *
 * @param
 * @return
 * @throws
 */
public interface PlantService extends BaseService<PlantEntity , PlantCreationRequest, PlantResponse> {
    Optional<PlantResponse> updateInfo(String id, PlantUpdateInfoRequest PlantUpdateInfoRequest);

}
