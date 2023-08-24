package com.Mediscreen.AppReport.controller;

import com.Mediscreen.AppReport.model.Report;
import com.Mediscreen.AppReport.model.RiskFactor;
import com.Mediscreen.AppReport.model.RiskLevel;
import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;

import com.Mediscreen.AppReport.service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    @Autowired
    ReportService reportService;

    @PostMapping("/report/generate")
    public Report generateAReport(@RequestParam(name = "birthdate") String birthdateString , @RequestParam(name = "gender") String gender , @RequestBody List<PatientAssesmentDTO> list) throws ParseException {

        Date birthdate = new SimpleDateFormat("yyyy/MM/dd").parse(birthdateString);

        List<RiskFactor> listOfRiskFactorFound = reportService.searchForFactorsInAssessments(list);
        RiskLevel riskLevelEvaluated = reportService.evaluateRiskLevel(listOfRiskFactorFound, birthdate, gender);
        Report report =  reportService.generateTheReport(birthdate,riskLevelEvaluated );
        return report;
    }

}
