package vn.edu.likelion.farm_management.service.farm;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.likelion.farm_management.common.exceptions.AppException;
import vn.edu.likelion.farm_management.common.exceptions.ErrorCode;
import vn.edu.likelion.farm_management.dto.request.farm.FarmCreationRequest;
import vn.edu.likelion.farm_management.dto.response.dashboard.MonthlyPlantHarvestSummaryDTO;
import vn.edu.likelion.farm_management.dto.response.dashboard.HarvestReport;
import vn.edu.likelion.farm_management.dto.response.dashboard.YieldAndMoneyDashboard;
import vn.edu.likelion.farm_management.dto.response.farm.AllFarmGeneralResponse;
import vn.edu.likelion.farm_management.dto.response.farm.FarmGeneralResponse;
import vn.edu.likelion.farm_management.entity.FarmEntity;
import vn.edu.likelion.farm_management.mapper.FarmMapper;
import vn.edu.likelion.farm_management.repository.FarmRepository;
import vn.edu.likelion.farm_management.repository.HarvestRepository;
import vn.edu.likelion.farm_management.repository.PlantRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
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
    HarvestRepository harvestRepository;

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

        try {
            // Cập nhật farm
            FarmEntity farmEntityUpdate = farmRepository.save(farmEntity);

            return Optional.of(farmGeneralResponse);

        } catch (Exception e) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
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

    @Override
    public AllFarmGeneralResponse getTotalPlantedAreaAllFarm() {

        List<Object[]> list = farmRepository.getTotalPlantedAreaAllFarm();
        if (list.isEmpty()) {
            throw new AppException(ErrorCode.QUERY_NOT_FOUND);
        }
        AllFarmGeneralResponse allFarmGeneralResponse = new AllFarmGeneralResponse();
                Object[] objects = list.get(0);
        FarmRepository.toAllFarmGeneralResponse(allFarmGeneralResponse, objects);
        return allFarmGeneralResponse;
    }

    @Override
    public YieldAndMoneyDashboard getMonthlyPlantAndHarvestSummary() {
        YieldAndMoneyDashboard yieldAndMoneyDashboard = new YieldAndMoneyDashboard();
        List<Object[]> list = farmRepository.getMonthlyPlantAndHarvestSummary();
        if (list.isEmpty()) {
            throw new AppException(ErrorCode.QUERY_NOT_FOUND);
        }

        list.forEach(
                item -> {
                    MonthlyPlantHarvestSummaryDTO monthlyPlantHarvestSummaryDTO = new MonthlyPlantHarvestSummaryDTO();
                    FarmRepository.toMonthlyPlantHarvestSummaryDTO(monthlyPlantHarvestSummaryDTO, item);
                    yieldAndMoneyDashboard.getMonthlyPlantHarvestSummaryDTOArrayList().add(monthlyPlantHarvestSummaryDTO);
                }
        );
        return yieldAndMoneyDashboard;
    }



    @Override
    public ByteArrayInputStream getReportDashboard(int month, int year) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            // Tạo một sheet mới trong workbook
            Sheet sheet = workbook.createSheet("Harvest Report");

            // Định dạng cho tiêu đề
            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Row headerRow = sheet.createRow(0);

            // Tạo tiêu đề cho các cột
            String[] headers = {"Month", "Year", "Plant ID", "Plant Name", "Farm ID", "Farm Name",
                    "Type Plant ID", "Type Plant Name", "Total Yield Planted",
                    "Total Money Planted", "Total Yield Actual", "Total Money Actual"};

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // Lấy dữ liệu từ cơ sở dữ liệu
            List<Object[]> data = farmRepository.getReportDashboard(month, year);
            if (data.isEmpty()) {
                throw new AppException(ErrorCode.HARVEST_NOT_EXIST);
            }
            int rowIdx = 1;

            // Định dạng cho các ô dữ liệu
            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setWrapText(true); // Text wrapping

            // Chuyển đổi dữ liệu sang các đối tượng HarvestReport và điền vào sheet
            for (Object[] objects : data) {
                HarvestReport harvestReport = new HarvestReport();
                FarmRepository.toHarvestReport(harvestReport, objects);

                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(month); // Thêm tháng
                row.createCell(1).setCellValue(year);  // Thêm năm
                row.createCell(2).setCellValue(harvestReport.getPlantId());
                row.createCell(3).setCellValue(harvestReport.getPlantName());
                row.createCell(4).setCellValue(harvestReport.getFarmId());
                row.createCell(5).setCellValue(harvestReport.getFarmName());
                row.createCell(6).setCellValue(harvestReport.getTypePlantId());
                row.createCell(7).setCellValue(harvestReport.getTypePlantName());
                row.createCell(8).setCellValue(harvestReport.getTotalYieldPlanted() != null ? harvestReport.getTotalYieldPlanted() : 0);
                row.createCell(9).setCellValue(harvestReport.getTotalMoneyPlanted() != null ? harvestReport.getTotalMoneyPlanted() : 0);
                row.createCell(10).setCellValue(harvestReport.getTotalYieldActual() != null ? harvestReport.getTotalYieldActual() : 0);
                row.createCell(11).setCellValue(harvestReport.getTotalMoneyActual() != null ? harvestReport.getTotalMoneyActual() : 0);

                // Áp dụng định dạng cho các ô dữ liệu
                for (Cell cell : row) {
                    cell.setCellStyle(dataStyle);
                }
            }

            // Đặt kích thước tự động cho các cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi workbook vào output stream và trả về ByteArrayInputStream
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
