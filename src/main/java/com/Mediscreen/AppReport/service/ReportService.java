package com.Mediscreen.AppReport.service;

import com.Mediscreen.AppReport.model.Report;
import com.Mediscreen.AppReport.model.RiskFactor;
import com.Mediscreen.AppReport.model.RiskLevel;
import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;
import com.Mediscreen.AppReport.model.dto.PatientDTO;

import java.util.Date;
import java.util.List;


public interface ReportService {

    int calculatePatientAge(Date birthdate);

    List<RiskFactor> searchForFactorsInAssessments(List<PatientAssesmentDTO> list);

    RiskLevel evaluateRiskLevel (List<RiskFactor> factorsOfRiskFound, Date birthdate, String gender);

    Report generateTheReport(Date birthdate, RiskLevel riskLevel);


}
