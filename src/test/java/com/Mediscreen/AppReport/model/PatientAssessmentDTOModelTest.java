package com.Mediscreen.AppReport.model;

import com.Mediscreen.AppReport.model.dto.PatientAssesmentDTO;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PatientAssessmentDTOModelTest {

    @Test
    public void testHashCode()
    {
        PatientAssesmentDTO patientAssessment = new PatientAssesmentDTO();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        assertNotNull(patientAssessment.hashCode());
    }

    @Test
    public void testEquals()
    {
        PatientAssesmentDTO patientAssessment = new PatientAssesmentDTO();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        PatientAssesmentDTO patientAssessment2 = new PatientAssesmentDTO();
        patientAssessment2.setPatientId(0);
        patientAssessment2.setDate(new Date());
        patientAssessment2.setAssessment("assessmentTest");

        PatientAssesmentDTO patientAssessment3 = new PatientAssesmentDTO();
        patientAssessment3.setPatientId(0);
        patientAssessment3.setDate(new Date());
        patientAssessment3.setAssessment("assessmentTest3");


        assertTrue(patientAssessment.equals(patientAssessment));

        assertTrue(patientAssessment.equals(patientAssessment2));

        patientAssessment2 = null;

        assertFalse(patientAssessment.equals(patientAssessment2));

        assertFalse((new PatientAssesmentDTO().equals(patientAssessment)));

        assertFalse(patientAssessment.equals(patientAssessment3));

        patientAssessment3.setAssessment("assessmentTest");

        patientAssessment3.setPatientId(11);

        assertFalse(patientAssessment.equals(patientAssessment3));
    }

    @Test
    public void testToString()
    {
        PatientAssesmentDTO patientAssessment = new PatientAssesmentDTO();
        patientAssessment.setPatientId(0);
        patientAssessment.setDate(new Date());
        patientAssessment.setAssessment("assessmentTest");

        assertNotNull(patientAssessment.toString());
    }
}
