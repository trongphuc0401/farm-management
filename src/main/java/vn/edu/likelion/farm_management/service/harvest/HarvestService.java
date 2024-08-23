package vn.edu.likelion.farm_management.service.harvest;

import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.entity.HarvestEntity;
import vn.edu.likelion.farm_management.service.BaseService;

/**
 * HarvestService -
 *
 * @param
 * @return
 * @throws
 */
public interface HarvestService extends BaseService<HarvestEntity, HarvestCreationRequest, HarvestResponse,HarvestCreationRequest> {
}
