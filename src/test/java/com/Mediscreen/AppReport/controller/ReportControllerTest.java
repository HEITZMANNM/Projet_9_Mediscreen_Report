package com.Mediscreen.AppReport.controller;


import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;
import com.Mediscreen.AppReport.model.dto.PatientDTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testToGetAPatientReport() throws Exception {

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

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setGender("F");


        this.mockMvc.perform(MockMvcRequestBuilders.post("/report/generate")
                .param("birthdate", "1990/12/12")
                        .param("gender", "F")
                .content(asJsonString(list))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.patientAge").value(32))
                .andExpect(MockMvcResultMatchers.jsonPath("$.riskLevel").value("BORDERLINE"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
