package vn.edu.likelion.farm_management.service.farm;

import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.dashboard.YieldAndMoneyDashboard;
import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.service.BaseService;

/**
 * FarmService -
 *
 * @param
 */
public interface FarmService extends BaseService<FarmEntity, FarmCreationRequest, FarmGeneralResponse, FarmCreationRequest> {
    AllFarmGeneralResponse getTotalPlantedAreaAllFarm();

    YieldAndMoneyDashboard getMonthlyPlantAndHarvestSummary();

}
