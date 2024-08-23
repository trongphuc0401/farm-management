package vn.edu.likelion.farm_management.service.farm;

import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.service.BaseService;

import java.util.List;
import java.util.Optional;

/**
 * FarmService -
 *
 * @param
 */
public interface FarmService
        extends BaseService<FarmEntity, FarmCreationRequest, FarmGeneralResponse, FarmCreationRequest> {

}
