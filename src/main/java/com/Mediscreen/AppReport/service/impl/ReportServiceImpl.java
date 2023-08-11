package com.Mediscreen.AppReport.service.impl;

import com.Mediscreen.AppReport.model.Report;
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
            Date nowDate = new Date();

            Double ageInDouble = (nowDate.getTime()-birthdate.getTime())/3.154e+10;
            patientsAge =  ageInDouble.intValue();

            log.info("The patient's age has been calculated");

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
                    String factorInFrench = factor.getFactorInFrench();
                    if(assessment.contains(factorInFrench)){
                        if(!numberOfFactorInAssessments.contains(factor)) {
                            numberOfFactorInAssessments.add(factor);
                        }
                    }
                }
            }

            log.info("The list of number of factor find in the patient's assessments has been created");
        }
        catch (Exception ex){
            log.error("Error to create the list of number of factor find in the patient's assessments");
        }
        return numberOfFactorInAssessments;
    }

    /**
     *Methode which evaluate the risk level for a patient to develops diabetes,
     * according to his gender, age and number of factors assessed by the doctor
     * @param factorsOfRiskFound -  list of all factors written by doctors
     * @param birthdate -  the patient's birthdate
     */

    @Override
    public RiskLevel evaluateRiskLevel(List<RiskFactor> factorsOfRiskFound, Date birthdate, String gender) {

        RiskLevel riskLevel= RiskLevel.NONE;
        int patientAge = this.calculatePatientAge(birthdate);

        try{
            if(factorsOfRiskFound.size() <= 1){
                return riskLevel;
            }
            else{
                if (patientAge>30) {
                    if(factorsOfRiskFound.size() < 6){
                        riskLevel = RiskLevel.BORDERLINE;
                    } else if (factorsOfRiskFound.size() < 8) {
                        riskLevel = RiskLevel.DANGER;
                    }
                    else{
                        riskLevel = RiskLevel.EARLY_ONSET;
                    }
                }
                else {
                    if(gender.equals("M") && factorsOfRiskFound.size() > 2){
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
                        else {
                            riskLevel = RiskLevel.EARLY_ONSET;
                        }
                    }
                }
            }

            log.info("The risk level has been evaluated");
        }
        catch (Exception ex){
            log.error("Error the evaluate the patient's rick level");
        }
        return riskLevel;
    }

    @Override
    public Report generateTheReport(Date birthdate, RiskLevel riskLevel) {

        Report report = new Report();

        try{

            report.setPatientAge(this.calculatePatientAge(birthdate));
            report.setRiskLevel(riskLevel);

            log.info("The patient report has been generated");
        }
        catch (Exception ex){
            log.error("Error to generate the patient's report");
        }
        return report;
    }

}
