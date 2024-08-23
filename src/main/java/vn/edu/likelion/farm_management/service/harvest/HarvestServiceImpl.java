package vn.edu.likelion.farm_management.service.harvest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.entity.HarvestEntity;
import vn.edu.likelion.farm_management.mapper.HarvestMapper;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.HarvestRepository;
import vn.edu.likelion.farm_management.repository.PlantRepository;

import java.util.List;
import java.util.Optional;

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
    HarvestMapper harvestMapper;


    @Override
    public Optional<HarvestResponse> create(HarvestCreationRequest harvestCreationRequest) {

        HarvestEntity harvestEntity = harvestMapper.toCreateHarvest(harvestCreationRequest);
        harvestEntity = harvestRepository.save(harvestEntity);
        HarvestResponse harvestResponse = harvestMapper.toHarvestResponse(harvestEntity);
        return Optional.of(harvestResponse);
    }

    @Override public Optional<HarvestResponse> update(HarvestCreationRequest harvestCreationRequest) {
        return Optional.empty();
    }

    @Override public Optional<HarvestResponse> updateInfo(String id, HarvestCreationRequest harvestCreationRequest) {
        return Optional.empty();
    }

    @Override public List<HarvestResponse> saveAll(List<HarvestEntity> ts) {
        return List.of();
    }

    @Override public void delete(String id) {

    }

    @Override public void deleteAll(List<String> listId) {

    }

    @Override public Optional<HarvestResponse> findById(String id) {
        return Optional.empty();
    }

    @Override public List<HarvestResponse> findAll() {
        return List.of();
    }
}
