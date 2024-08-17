package vn.edu.likelion.farm_management.service.plant;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.entity.PlantEntity;
import vn.edu.likelion.farm_management.repository.PlantRepository;

import java.util.List;
import java.util.Optional;

/**
 * PlantServiceImpl -
 *
 * @param
 * @return
 * @throws
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlantServiceImpl implements PlantService{

    @Autowired
    PlantRepository plantRepository;

    @Override
    public Optional<PlantEntity> save(PlantEntity plantEntity) {

        if (plantRepository.existsById(plantEntity.getId())) {
            throw new AppException(ErrorCode.PLANT_EXIST);
        }
        return Optional.of(plantRepository.save(plantEntity));
    }

    @Override
    public List<PlantEntity> saveAll(List<PlantEntity> plantEntities) {
        return List.of();
    }

    @Override
    public void delete(PlantEntity plantEntity) {

    }

    @Override
    public void deleteAll(List<PlantEntity> plantEntities) {

    }

    @Override
    public Optional<PlantEntity> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<PlantEntity> getAll() {
        return List.of();
    }
}
