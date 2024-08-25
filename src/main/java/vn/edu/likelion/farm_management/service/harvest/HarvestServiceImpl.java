package vn.edu.likelion.farm_management.service.harvest;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.common.utils.DateTimeUtils;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationAllRequest;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestGroupDateResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponsePaginate;
import vn.edu.likelion.farm_management.dto.response.plant.PlantResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.entity.HarvestEntity;

import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.mapper.HarvestMapper;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.repository.HarvestRepository;
import vn.edu.likelion.farm_management.repository.PlantRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * HarvestServiceImpl -
 *
 * @param
 * @return
 * @throws
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HarvestServiceImpl implements HarvestService{

    @Autowired
    HarvestRepository harvestRepository;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    HarvestMapper harvestMapper;

    @Autowired
    PlantMapper plantMapper;

    @Autowired
    FarmRepository farmRepository;


    @Override
    public Optional<HarvestResponse> create(HarvestCreationRequest harvestCreationRequest) {

        HarvestEntity harvestEntity = harvestMapper.toCreateHarvest(harvestCreationRequest);
        harvestEntity = harvestRepository.save(harvestEntity);
        HarvestResponse harvestResponse = harvestMapper.toHarvestResponse(harvestEntity);
        return Optional.of(harvestResponse);

    }

    @Override
    public Optional<HarvestResponse> update(HarvestCreationRequest harvestCreationRequest) {
        return Optional.empty();
    }

    @Override
    public Optional<HarvestResponse> updateInfo(String id, HarvestCreationRequest harvestCreationRequest) {
        HarvestEntity harvestEntity = harvestRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HARVEST_NOT_EXIST));

        harvestEntity.setUpdateAt(LocalDateTime.now());

        harvestMapper.updateEntity(harvestEntity, harvestCreationRequest);

        try {
            HarvestEntity harvestEntityUpdate = harvestRepository.save(harvestEntity);

            return Optional.ofNullable(harvestMapper.toHarvestResponse(harvestEntityUpdate));

        } catch (Exception e) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
    }

    @Override
    public List<HarvestResponse> saveAll(List<HarvestEntity> ts) {
        return List.of();

    }

    @Override
    public void delete(String id) {
        HarvestEntity harvestEntity = harvestRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HARVEST_NOT_EXIST));
        harvestEntity.setIsDeleted(1);
        try {
            HarvestEntity harvestEntityUpdate = harvestRepository.save(harvestEntity);
        } catch (Exception e) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
    }

    @Override
    public void deleteAll(List<String> listId) {

    }

    @Override
    public Optional<HarvestResponse> findById(String id) {
        HarvestResponse harvestResponse =
                harvestRepository.findById(id).map(harvestMapper::toHarvestResponse)
                        .orElseThrow(() -> new AppException(ErrorCode.HARVEST_NOT_EXIST));
        return Optional.ofNullable(harvestResponse);
    }

    @Override
    public List<HarvestResponse> findAll() {
        List<HarvestResponse> harvestResponses = harvestRepository.findAll()
                .stream()
                .map(harvestMapper::toHarvestResponse)
                .toList();
        if (harvestResponses.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }
        return harvestResponses;
    }

    @Override
    public HarvestResponsePaginate getAllHarvestByDate(String date, int pageNo, int pageSize) {

        LocalDate localDate = DateTimeUtils.convertStringToLocalDate(date);
        System.out.println(localDate);

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createAt").descending());
        Page<HarvestEntity> harvestEntities =
                harvestRepository.findAllByCreateAt(localDate, pageable);

        if (harvestEntities.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }
        List<HarvestEntity> harvestEntityList = harvestEntities.getContent();

        try{
            List<HarvestResponse> data = harvestEntityList.stream().map(harvestMapper::toHarvestResponse).toList();

            HarvestResponsePaginate harvestResponsePaginate = new HarvestResponsePaginate();
            harvestResponsePaginate.setResults(data);
            harvestResponsePaginate.setPageNo(harvestEntities.getNumber());
            harvestResponsePaginate.setPageSize(harvestEntities.getSize());
            harvestResponsePaginate.setTotalElements(harvestEntities.getNumberOfElements());
            harvestResponsePaginate.setTotalPages(harvestEntities.getTotalPages());
            return harvestResponsePaginate;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.RESPONSE_DTO_CONVERSION_FAILED);
        }

    }

    @Override
    public List<HarvestGroupDateResponse> getAllMoneyAndYieldGroupDate() {
        List<HarvestGroupDateResponse> harvestGroupDateResponses = new ArrayList<>();

        List<Object[]> list = harvestRepository.getAllMoneyAndYieldGroupDate();

        if (list.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }

        list.forEach(
                item -> {
                    HarvestGroupDateResponse harvestGroupDateResponse = new HarvestGroupDateResponse();
                    HarvestRepository.toHarvestGroupDateResponse(harvestGroupDateResponse, item);
                    harvestGroupDateResponses.add(harvestGroupDateResponse);
                }
        );

        if (harvestGroupDateResponses.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }
        return harvestGroupDateResponses;
    }

    @Override
    @Transactional
    public List<HarvestResponse> harvestByNumber(HarvestCreationRequest harvestCreationRequest) {

        List<HarvestResponse> harvestResponses = new ArrayList<>();

            // Lấy danh sách các cây có status là Harvested
            List<PlantEntity> readyToHarvestPlants  = plantRepository.findReadyToHarvestPlants(LocalDateTime.now());

            if (readyToHarvestPlants.isEmpty()) {
                throw new AppException(ErrorCode.NO_PLANTS_READY_TO_HARVEST);
            }

            int quantityToHarvest = Math.min(harvestCreationRequest.getQuantity(), readyToHarvestPlants.size());


            String farmName = "";
            for (int i = 0; i < quantityToHarvest; i++) {
                PlantEntity plantEntity = readyToHarvestPlants.get(i);
                Optional<FarmEntity> farmEntity = farmRepository.findById(plantEntity.getFarmId());

                if (farmEntity.isPresent()) {
                    FarmEntity farm = farmEntity.get();

                    farmName = farm.getName();

                }

                HarvestResponse response = new HarvestResponse();
                response.setId(UUID.randomUUID().toString());
                response.setPlantId(plantEntity.getId());
                response.setPlantName(plantEntity.getName());
                response.setTypePlantId(plantEntity.getTypePlant().getId());
                response.setFarmId(harvestCreationRequest.getFarmId());
                response.setFarmName(farmName);
                response.setDescription(harvestCreationRequest.getDescription());
                response.setYieldActual(harvestCreationRequest.getYieldActual() / quantityToHarvest);
                response.setPriceActual(harvestCreationRequest.getPriceActual());
                response.setIsDeleted(0);
                response.setCreateAt(LocalDateTime.now());

                harvestResponses.add(response);

                plantEntity.setIsDeleted(1);
                plantRepository.save(plantEntity);
            }

            harvestRepository.saveAll(harvestResponses);

            return harvestResponses;
    }

    @Override public List<PlantResponse> findAllPlantReadyHarvestByFarmId(String farmId) {

        List<PlantEntity> allPlantsInFarm = plantRepository.findPlantByFarmId(farmId);
            if (allPlantsInFarm.isEmpty()) {
                throw new AppException(ErrorCode.PLANT_NOT_EXIST);
            }

        List<PlantEntity> readyToHarvestPlants = plantRepository.findReadyToHarvestPlants(LocalDateTime.now());

        List<PlantEntity> plantsReadyToHarvestInFarm = new ArrayList<>();

        for(PlantEntity plantEntity : allPlantsInFarm) {
            if (readyToHarvestPlants.contains(plantEntity)) {
                plantsReadyToHarvestInFarm.add(plantEntity);
            }
        }

            if (plantsReadyToHarvestInFarm.isEmpty()) {
            throw new AppException(ErrorCode.NO_PLANTS_READY_TO_HARVEST);
        }
        return plantsReadyToHarvestInFarm.stream()
                .map(plantMapper::toPlantResponse).toList();
    }


    @Override public List<HarvestResponse> harvestAll(HarvestCreationAllRequest harvestCreationAllRequests) {
        List<HarvestResponse> harvestResponses = new ArrayList<>();

        // Lấy danh sách các cây có status là Harvested
        List<PlantEntity> readyToHarvestPlants  = plantRepository.findReadyToHarvestPlants(LocalDateTime.now());

        if (readyToHarvestPlants.isEmpty()) {
            throw new AppException(ErrorCode.NO_PLANTS_READY_TO_HARVEST);
        }

        String farmName = "";
        for (PlantEntity plantEntity : readyToHarvestPlants) {

            Optional<FarmEntity> farmEntity = farmRepository.findById(plantEntity.getFarmId());

            if (farmEntity.isPresent()) {
                FarmEntity farm = farmEntity.get();

                farmName = farm.getName();

            }
            HarvestResponse response = new HarvestResponse();

            response.setId(UUID.randomUUID().toString());
            response.setPlantId(plantEntity.getId());
            response.setPlantName(plantEntity.getName());
            response.setFarmId(harvestCreationAllRequests.getFarmId());
            response.setFarmName(farmName);
            response.setDescription(harvestCreationAllRequests.getDescription());
            response.setYieldActual(harvestCreationAllRequests.getYieldActual() / readyToHarvestPlants.size());
            response.setPriceActual(harvestCreationAllRequests.getPriceActual());
            response.setIsDeleted(0);
            response.setCreateAt(LocalDateTime.now());
            response.setTypePlantId(plantEntity.getTypePlant().getId());
            harvestResponses.add(response);
            plantEntity.setIsDeleted(1);

            plantRepository.save(plantEntity);


        }
        harvestRepository.saveAll(harvestResponses);

        return harvestResponses;
    }

    @Override public List<HarvestResponse> harvestByListPlant(List<String> plantIds,
                                                              HarvestCreationAllRequest harvestCreationAllRequests) {
        List<HarvestResponse> harvestResponses = new ArrayList<>();

        // Lấy danh sách các cây có status là Harvested
        List<PlantEntity> plantsReadyForHarvest  = plantRepository.findPlantsReadyForHarvest(plantIds,LocalDateTime.now());

        if (plantsReadyForHarvest.isEmpty()) {
            throw new AppException(ErrorCode.NO_PLANTS_READY_TO_HARVEST);
        }

        String farmName = "";
        for (PlantEntity plantEntity : plantsReadyForHarvest) {

            Optional<FarmEntity> farmEntity = farmRepository.findById(plantEntity.getFarmId());

            if (farmEntity.isPresent()) {
                FarmEntity farm = farmEntity.get();

                farmName = farm.getName();

            }
            HarvestResponse response = new HarvestResponse();

            response.setId(UUID.randomUUID().toString());
            response.setPlantId(plantEntity.getId());
            response.setPlantName(plantEntity.getName());
            response.setFarmId(harvestCreationAllRequests.getFarmId());
            response.setFarmName(farmName);
            response.setDescription(harvestCreationAllRequests.getDescription());
            response.setYieldActual(harvestCreationAllRequests.getYieldActual() / plantsReadyForHarvest.size());
            response.setPriceActual(harvestCreationAllRequests.getPriceActual());
            response.setIsDeleted(0);
            response.setCreateAt(LocalDateTime.now());
            response.setTypePlantId(plantEntity.getTypePlant().getId());
            harvestResponses.add(response);
            plantEntity.setIsDeleted(1);

            plantRepository.save(plantEntity);


        }
        harvestRepository.saveAll(harvestResponses);

        return harvestResponses;
    }


    @Override
    public List<HarvestResponse> findAllByCreateAt(String date) {
        List<HarvestResponse> harvestResponses =
                harvestRepository.findAllByCreateAt(DateTimeUtils.convertStringToLocalDate(date)).stream()
                        .map(harvestMapper::toHarvestResponse).toList();
        if (harvestResponses.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }
        return harvestResponses;
    }

    @Override
    public List<HarvestResponse> findAllByOrderByCreateAtAsc() {
        List<HarvestResponse> harvestResponses =
                harvestRepository.findAllByOrderByCreateAtAsc().stream().map(harvestMapper::toHarvestResponse).toList();
        if (harvestResponses.isEmpty()) {
            throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
        }
        return harvestResponses;
    }
}
