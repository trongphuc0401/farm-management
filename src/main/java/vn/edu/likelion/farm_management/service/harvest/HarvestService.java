package vn.edu.likelion.farm_management.service.harvest;

import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationAllRequest;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestUpdateRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestGroupDateResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponsePaginate;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.HarvestEntity;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import java.util.List;

/**
 * HarvestService -
 *
 * @param
 * @return
 * @throws
 */
public interface HarvestService
        extends BaseService<HarvestEntity, HarvestCreationRequest, HarvestResponse, HarvestUpdateRequest> {

    HarvestResponsePaginate getAllHarvestByDate(String date, int pageNo, int pagSize);

    List<HarvestGroupDateResponse> getAllMoneyAndYieldGroupDate();

    List<HarvestResponse> harvestByNumber(HarvestCreationRequest harvestCreationRequest);

    List<PlantResponse> findAllPlantReadyHarvestByFarmId(String farmId);

    List<HarvestResponse> harvestAll(HarvestCreationAllRequest harvestCreationAllRequests);
    List<HarvestResponse> harvestByListPlant(List<String> listPlant, HarvestCreationAllRequest harvestCreationAllRequests);

    List<HarvestResponse> findAllByCreateAt(String date);

    List<HarvestResponse> findAllByOrderByCreateAtAsc();

}
