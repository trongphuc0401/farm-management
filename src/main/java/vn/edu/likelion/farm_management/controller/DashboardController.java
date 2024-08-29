package vn.edu.likelion.farm_management.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.likelion.farm_management.common.constants.ApiPath;
import vn.edu.likelion.farm_management.common.restfulAPI.ResponseUtil;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIResponse;
import vn.edu.likelion.farm_management.common.restfulAPI.RestAPIStatus;
import vn.edu.likelion.farm_management.service.farm.FarmService;
import vn.edu.likelion.farm_management.service.harvest.HarvestService;

import java.io.ByteArrayInputStream;

/**
 * DashboardController -
 *
 * @param
 * @return
 * @throws
 */

@RestController
@RequestMapping(ApiPath.DASHBOARD_API)
@RequiredArgsConstructor
public class DashboardController {


    @Autowired
    ResponseUtil responseUtil;

    @Autowired
    HarvestService harvestService;

    @Autowired
    FarmService farmService;

    @GetMapping("/getMoneyDashboard")
    public ResponseEntity<RestAPIResponse<Object>> getMoneyAllFarmEachMonthsGroupByTypePlant() {
        return responseUtil.buildResponse(RestAPIStatus.OK, farmService.getMonthlyPlantAndHarvestSummary(),
                HttpStatus.OK);
    }


    @GetMapping("/getReportDashboard")
    public ResponseEntity<byte[]> getReportDashboard(
            @RequestParam(value = "month") int month,
            @RequestParam(value = "year") int year
            ) {
        ByteArrayInputStream in = farmService.getReportDashboard(month, year);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=farm-data-"+month+"-"+year+".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(in.readAllBytes());
    }



}
