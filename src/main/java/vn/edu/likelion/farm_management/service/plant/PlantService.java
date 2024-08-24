package vn.edu.likelion.farm_management.service.plant;

import vn.edu.likelion.farm_management.dto.request.plant.PlantCreationRequest;
import vn.edu.likelion.farm_management.dto.request.plant.PlantUpdateInfoRequest;
import vn.edu.likelion.farm_management.dto.response.plant.PaginatePlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.dto.response.plant.TypePlantResponse;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import java.util.List;
import java.util.Optional;

/**
 * PlantService -
 *
 * @param
 * @return
 * @throws
 */

public interface PlantService extends BaseService<PlantEntity , PlantCreationRequest, PlantResponse, PlantUpdateInfoRequest> {
//    Optional<PlantResponse> updateInfo(String id, PlantUpdateInfoRequest PlantUpdateInfoRequest);

  
    Optional<PlantResponse> addPlantToFarm(String plantId,String farmId);

    PaginatePlantResponse getAllByPagination(int pageNo , int pagSize);

    List<TypePlantResponse> findAllTypePlant();

    List<PlantResponse> findAllPlantByFarm(String farmId);

    PaginatePlantResponse searchPlantsByPagination(String searchText, int pageNo , int pagSize);



}
