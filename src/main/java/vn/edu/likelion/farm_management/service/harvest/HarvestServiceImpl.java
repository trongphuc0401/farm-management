package vn.edu.likelion.farm_management.service.harvest;

import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.entity.HarvestEntity;

import java.util.List;
import java.util.Optional;

/**
 * HarvestServiceImpl -
 *
 * @param
 * @return
 * @throws
 */
public class HarvestServiceImpl implements HarvestService{
    @Override public Optional<HarvestResponse> create(HarvestCreationRequest t) {
        return Optional.empty();
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
