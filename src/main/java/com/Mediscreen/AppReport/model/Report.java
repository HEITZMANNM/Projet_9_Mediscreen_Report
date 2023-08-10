package com.Mediscreen.AppReport.model;

import com.Mediscreen.AppReport.model.dto.PatientDTO;
import lombok.Data;

@Data
public class Report {

    PatientDTO patientDTO;

    RiskLevel riskLevel;

}
