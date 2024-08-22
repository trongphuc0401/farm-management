package vn.edu.likelion.farm_management.service.farm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.mapper.FarmMapper;
import vn.edu.likelion.farm_management.mapper.PlantMapper;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.repository.TypePlantRepository;

import java.util.List;
import java.util.Optional;

/**
 * FarmServiceImpl -
 *
 * @param
 * @return
 * @throws
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FarmServiceImpl implements FarmService {

    @Autowired
    FarmRepository farmRepository;

    @Autowired
    FarmMapper farmMapper;


    @Override
    public Optional<FarmGeneralResponse> create(FarmCreationRequest t) {
        return Optional.empty();
    }

    @Override
    public Optional<FarmGeneralResponse> update(FarmCreationRequest t) {
        return Optional.empty();
    }

    @Override
    public List<FarmGeneralResponse> saveAll(List<FarmEntity> ts) {
        return List.of();
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void deleteAll(List<String> listId) {

    }

    @Override
    public Optional<FarmGeneralResponse> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<FarmGeneralResponse> findAll() {
        return List.of();
    }
}
