package com.Mediscreen.AppReport.service.impl;

import com.Mediscreen.AppReport.model.RiskFactor;
import com.Mediscreen.AppReport.model.RiskLevel;
import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;
import com.Mediscreen.AppReport.model.dto.PatientDTO;
import com.Mediscreen.AppReport.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Override
    public int calculatePatientAge(Date birthdate) {

        int patientsAge=0;

        try{
            Date now = new Date();
            Double ageInDouble = (now.getTime()-birthdate.getTime())/3.154e+10;
            patientsAge =  ageInDouble.intValue();

            log.info("The patient's age was calculated");

        }
        catch (Exception ex){
            log.error("Error to calculate the patient's age");
        }
        return patientsAge;
    }

    @Override
    public List<RiskFactor> searchForFactorsInAssessments(List<PatientAssesmentDTO> list) {

        List<RiskFactor> numberOfFactorInAssessments = new ArrayList<>();

        RiskFactor[] listOfAllFactors = RiskFactor.values();

        try{
            for(PatientAssesmentDTO patientAssesmentDTO: list){
                String assessment = patientAssesmentDTO.getAssessment();

                for(RiskFactor factor: listOfAllFactors){
                    if(assessment.contains(factor.getFactorsInFrench())){
                        if(!numberOfFactorInAssessments.contains(factor)) {
                            numberOfFactorInAssessments.add(factor);
                        }
                    }
                }
            }

            log.info("The list of number of factor find in the patient's assessments was created");
        }
        catch (Exception ex){
            log.error("Error to create the list of number of factor find in the patient's assessments");
        }
        return numberOfFactorInAssessments;
    }

    /**
    *Methode which evaluate the risk level for a patient to develops diabetes,
     * according to his gender, age and number of factors assessed by the doctor
    * @param - factorsOfRiskFound, list of all factors written by doctors
     * @param - patientDTO, the patient's information
     */

    @Override
    public RiskLevel evaluateRiskLevel(List<RiskFactor> factorsOfRiskFound, PatientDTO patientDTO) {

        RiskLevel riskLevel= RiskLevel.NONE;
        int patientAge = this.calculatePatientAge(patientDTO.getBirthDate());

        try{
            if(factorsOfRiskFound.size() <= 1){
                return riskLevel;
            }
             else{
                 if (patientAge>30) {
                    if(factorsOfRiskFound.size() < 6){
                        riskLevel = RiskLevel.BORDERLINE;
                    } else if (factorsOfRiskFound.size() >= 6 && factorsOfRiskFound.size() < 8) {
                        riskLevel = RiskLevel.DANGER;
                    }
                    else{
                        riskLevel = RiskLevel.EARLY_ONSET;
                    }
                }
                else {
                    if(patientDTO.getGender().equals("M") && factorsOfRiskFound.size() > 2){
                        if(factorsOfRiskFound.size() < 5){
                            riskLevel = RiskLevel.DANGER;
                        }
                        else {
                            riskLevel = RiskLevel.EARLY_ONSET;
                        }
                    }
                    else if (factorsOfRiskFound.size() >= 4){
                        if (factorsOfRiskFound.size() < 7){
                            riskLevel = RiskLevel.DANGER;
                        }
                        riskLevel = RiskLevel.EARLY_ONSET;
                    }
                }
            }
        }
        catch (Exception ex){

        }
        return riskLevel;
    }

}
