package vn.edu.likelion.farm_management.service.farm;

import vn.edu.likelion.farm_management.dto.request.farm.FarmAddNewPlantRequest;
import vn.edu.likelion.farm_management.dto.request.farm.FarmAddPlantRequest;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.dashboard.HarvestReport;
import vn.edu.likelion.farm_management.dto.response.dashboard.YieldAndMoneyDashboard;
import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.service.BaseService;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * FarmService -
 *
 * @param
 */
public interface FarmService extends BaseService<FarmEntity, FarmCreationRequest, FarmGeneralResponse, FarmCreationRequest> {
    AllFarmGeneralResponse getTotalPlantedAreaAllFarm();

    YieldAndMoneyDashboard getMonthlyPlantAndHarvestSummary();

    ByteArrayInputStream getReportDashboard(int month, int year);

    boolean addPlantToFarmByListPlantId(FarmAddPlantRequest farmAddPlantRequest);

    boolean addNewPlantToFarmBaseOnQuantity(FarmAddNewPlantRequest farmAddNewPlantRequest);
}
