package vn.edu.likelion.farm_management.service.farm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.mapper.FarmMapper;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.repository.PlantRepository;
import vn.edu.likelion.farm_management.repository.TypePlantRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * FarmServiceImpl -
 *
 * @param
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FarmServiceImpl implements FarmService {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    FarmMapper farmMapper;


    @Override
    public Optional<FarmGeneralResponse> create(FarmCreationRequest farmCreationRequest) {
        FarmEntity farmEntity = farmMapper.toCreateFarm(farmCreationRequest);
        farmEntity = farmRepository.save(farmEntity);
        FarmGeneralResponse farmGeneralResponse = farmMapper.toFarmGeneralResponse(farmEntity);
        return Optional.of(farmGeneralResponse);
    }

    @Override
    public Optional<FarmGeneralResponse> update(FarmCreationRequest t) {
        return Optional.empty();
    }

    @Override
    public Optional<FarmGeneralResponse> updateInfo(String id, FarmCreationRequest t) {

        // Kiểm tra tồn tại nông trại
        FarmEntity farmEntity = farmRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.FARM_NOT_EXIST));
        farmEntity.setUpdateAt(LocalDateTime.now());
        // Cập nhật nông trại Entity thông qua Mapper
        farmMapper.updateEntity(farmEntity, t);

        // Lấy dữ liệu tổng diện tích cây trồng trong farm
        Double area = farmEntity.getArea();
        Double area_planted = 0.00;

        FarmGeneralResponse farmGeneralResponse = farmMapper.toFarmGeneralResponse(farmEntity);
        List<Object[]> list = farmRepository.findFarmInformationToFarmResponse(id);
        if (list.isEmpty()) {
            area_planted = 0.00;
        }
        Object[] objects = list.get(0);
        FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);
        area_planted = (Double) objects[2];

        // Kiểm tra nếu diện tích mới cập nhật không phù hợp
        if ( area < area_planted) {
            throw new AppException(ErrorCode.FARM_UPDATE_AREA_FAIL);
        }

        // Cập nhật farm
        FarmEntity farmEntityUpdate = farmRepository.save(farmEntity);
        return Optional.of(farmGeneralResponse);
    }

    @Override
    public List<FarmGeneralResponse> saveAll(List<FarmEntity> ts) {
        return List.of();
    }

    @Override
    public void delete(String id) {
        FarmEntity farmEntity = farmRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.FARM_NOT_EXIST));
        farmEntity.setIsDeleted(1);
        farmRepository.save(farmEntity);
    }

    @Override
    public void deleteAll(List<String> listId) {
        listId.forEach(id -> {
            FarmEntity farmEntity = farmRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.FARM_NOT_EXIST));
            farmEntity.setIsDeleted(1);
            farmRepository.save(farmEntity);
        });
    }

    @Override
    public Optional<FarmGeneralResponse> findById(String id) {
        FarmGeneralResponse farmGeneralResponse =
                farmRepository.findById(id).map(farmMapper::toFarmGeneralResponse)
                        .orElseThrow(() -> new AppException(ErrorCode.FARM_NOT_EXIST));

        List<Object[]> list = farmRepository.findFarmInformationToFarmResponse(id);
        if (list.isEmpty()) {
            return Optional.of(farmGeneralResponse);
        }

        Object[] objects = list.get(0);
        FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);
        return Optional.of(farmGeneralResponse);
    }

    @Override
    public List<FarmGeneralResponse> findAll() {
        return farmRepository.findAll()
                        .stream()
                        .map(a -> {
                            FarmGeneralResponse farmGeneralResponse = farmMapper.toFarmGeneralResponse(a);
                            List<Object[]> list = farmRepository.findFarmInformationToFarmResponse(a.getId());
                            if (list.isEmpty()) {
                                return farmGeneralResponse;
                            }
                            Object[] objects = list.get(0);
                            FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);
                            return farmGeneralResponse;
                        }).toList();
    }
}
