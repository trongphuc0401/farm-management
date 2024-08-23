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
        FarmEntity farmEntity = farmRepository.findById(id).
                orElseThrow(() -> new AppException(ErrorCode.FARM_NOT_EXIST));

        farmMapper.updateEntity(farmEntity, t);

        FarmGeneralResponse farmGeneralResponse = farmMapper.toFarmGeneralResponse(farmEntity);

        List<Object[]> list = farmRepository.findFarmInformationToFarmResponse(id);
        Object[] objects = list.get(0);
        FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);

        Double area = farmEntity.getArea();

        Double area_planted = (Double) objects[2];

        if ( area < area_planted) {
            throw new AppException(ErrorCode.FARM_UPDATE_AREA_FAIL);
        }

        FarmEntity farmEntityUpdate = farmRepository.save(farmEntity);
        FarmGeneralResponse farmGeneralResponse2 = farmMapper.toFarmGeneralResponse(farmEntityUpdate);
        List<Object[]> list2 = farmRepository.findFarmInformationToFarmResponse(id);
        Object[] objects2 = list2.get(0);
        FarmRepository.toFarmGeneralResponse(farmGeneralResponse2, objects2);

        return Optional.of(farmGeneralResponse2);
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
        Object[] objects = list.get(0);
        FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);
        return Optional.of(farmGeneralResponse);
    }

    @Override
    public List<FarmGeneralResponse> findAll() {

        List<FarmGeneralResponse> list =
                farmRepository.findAll()
                        .stream()
                        .map(a -> {
                            FarmGeneralResponse farmGeneralResponse = farmMapper.toFarmGeneralResponse(a);
                            List<Object[]> list2 = farmRepository.findFarmInformationToFarmResponse(a.getId());
                            Object[] objects = list2.get(0);
                            FarmRepository.toFarmGeneralResponse(farmGeneralResponse, objects);
                            return farmGeneralResponse;
                        }).toList();
        return list;
    }


}
