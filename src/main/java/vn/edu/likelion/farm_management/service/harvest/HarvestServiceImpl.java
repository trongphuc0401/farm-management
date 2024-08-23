package vn.edu.likelion.farm_management.service.harvest;


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

import vn.edu.likelion.farm_management.dto.request.harvest.HarvestCreationRequest;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestGroupDateResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponse;
import vn.edu.likelion.farm_management.dto.response.harvest.HarvestResponsePaginate;
import vn.edu.likelion.farm_management.entity.HarvestEntity;

import vn.edu.likelion.farm_management.mapper.HarvestMapper;
import vn.edu.likelion.farm_management.repository.HarvestRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
