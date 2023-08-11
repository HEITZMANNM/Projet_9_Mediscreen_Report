package com.Mediscreen.AppReport;

import com.Mediscreen.AppReport.model.Report;
import com.Mediscreen.AppReport.model.RiskFactor;
import com.Mediscreen.AppReport.model.RiskLevel;
import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;
import com.Mediscreen.AppReport.model.dto.PatientDTO;
import com.Mediscreen.AppReport.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReportServiceTest {

    @Autowired
    ReportService reportService;

    @Test
    public void testToCalculatePatientAge() throws ParseException {

        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse("1993/10/10");
        Date now = new Date();

        Double ageInDouble = (now.getTime()-birthdate.getTime())/3.154e+10;
        int ageExpected  =  ageInDouble.intValue();

        int ageCalculated = reportService.calculatePatientAge(birthdate);

        assertEquals(ageExpected, ageCalculated);

    }

    @Test
    public void testToFindFactorsInAssessment(){

        PatientAssesmentDTO patientAssesmentDTOArnold1 = new PatientAssesmentDTO();
        patientAssesmentDTOArnold1.setAssessment("Le patient déclare qu'il fume depuis peu");
        patientAssesmentDTOArnold1.setDate(new Date());
        patientAssesmentDTOArnold1.setPatientId(1);

        PatientAssesmentDTO patientAssesmentDTOArnold2 = new PatientAssesmentDTO();
        patientAssesmentDTOArnold2.setAssessment("Tests de laboratoire indiquant une microalbumine élevée");
        patientAssesmentDTOArnold2.setDate(new Date());
        patientAssesmentDTOArnold2.setPatientId(1);

        PatientAssesmentDTO patientAssesmentDTOArnold3 = new PatientAssesmentDTO();
        patientAssesmentDTOArnold3.setAssessment("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière" +
                "Il se plaint également de crises d’apnée respiratoire anormales" +
                "Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
        patientAssesmentDTOArnold3.setDate(new Date());
        patientAssesmentDTOArnold3.setPatientId(1);

        PatientAssesmentDTO patientAssesmentDTOArnold4 = new PatientAssesmentDTO();
        patientAssesmentDTOArnold4.setAssessment("Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
        patientAssesmentDTOArnold4.setDate(new Date());
        patientAssesmentDTOArnold4.setPatientId(1);

        List<PatientAssesmentDTO> list = new ArrayList<>();
        list.add(patientAssesmentDTOArnold1);
        list.add(patientAssesmentDTOArnold2);
        list.add(patientAssesmentDTOArnold3);
        list.add(patientAssesmentDTOArnold4);


        List<RiskFactor> listOfFactorFound = reportService.searchForFactorsInAssessments(list);

        assertNotNull(listOfFactorFound);
        assertEquals(4, listOfFactorFound.size());
    }

    //Test if the evaluation of patient's risk level for a man aged over 30 with 3 factors assessed, return a Borderline risk
    @Test
    public void testToEvaluateThePatientRiskLevelForManBorderLineRisk() throws ParseException {

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setGender("M");
        patientDTO.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1990"));

        List<RiskFactor> listOfFactorFound = new ArrayList<>();
        listOfFactorFound.add(RiskFactor.ABNORMAL);
        listOfFactorFound.add(RiskFactor.HEMOGLOBIN_A1C);
        listOfFactorFound.add(RiskFactor.ANTIBODIES);

        RiskLevel riskLevelExpected = RiskLevel.BORDERLINE;

        RiskLevel riskLevelFound = reportService.evaluateRiskLevel(listOfFactorFound, patientDTO.getBirthDate(), patientDTO.getGender());

        assertEquals(riskLevelExpected, riskLevelFound);

    }

    //Test if the evaluation of patient's risk level for a woman aged under 30 with 4 factors assessed, return a Danger risk
    @Test
    public void testToEvaluateThePatientRiskLevelForYoungWoman() throws ParseException {

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setGender("F");
        patientDTO.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010"));

        List<RiskFactor> listOfFactorFound = new ArrayList<>();
        listOfFactorFound.add(RiskFactor.ABNORMAL);
        listOfFactorFound.add(RiskFactor.HEMOGLOBIN_A1C);
        listOfFactorFound.add(RiskFactor.ANTIBODIES);
        listOfFactorFound.add(RiskFactor.RELAPSE);

        RiskLevel riskLevelExpected = RiskLevel.DANGER;

        RiskLevel riskLevelFound = reportService.evaluateRiskLevel(listOfFactorFound, patientDTO.getBirthDate(), patientDTO.getGender());

        assertEquals(riskLevelExpected, riskLevelFound);

    }

    @Test
    public  void testToGenerateReport() throws ParseException {

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setGender("F");
        patientDTO.setBirthDate(new SimpleDateFormat("dd/MM/yyyy").parse("10/10/2010"));

        RiskLevel riskLevel = RiskLevel.BORDERLINE;

        Report report = reportService.generateTheReport(patientDTO.getBirthDate(), riskLevel);

        assertNotEquals(0, report.getPatientAge());

    }
}
