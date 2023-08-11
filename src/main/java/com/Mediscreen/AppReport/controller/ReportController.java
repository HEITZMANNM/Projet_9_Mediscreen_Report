package com.Mediscreen.AppReport.controller;

import com.Mediscreen.AppReport.model.Report;
import com.Mediscreen.AppReport.model.RiskFactor;
import com.Mediscreen.AppReport.model.RiskLevel;
import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;

import com.Mediscreen.AppReport.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report/generate")
    public Report generateAReport(@RequestParam(name = "birthdate") @DateTimeFormat(pattern="dd/MM/yyyy") Date birthdate , @RequestParam(name = "gender") String gender , @RequestBody List<PatientAssesmentDTO> list){

        List<RiskFactor> listOfRiskFactorFound = reportService.searchForFactorsInAssessments(list);
        RiskLevel riskLevelEvaluated = reportService.evaluateRiskLevel(listOfRiskFactorFound, birthdate, gender);
        Report report =  reportService.generateTheReport(birthdate,riskLevelEvaluated );
        return report;
    }

}
