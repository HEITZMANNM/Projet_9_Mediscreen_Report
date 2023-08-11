package com.Mediscreen.AppReport.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


public class ReportModelTest {

    @Test
    public void testHashCode()
    {
        Report report = new Report();
        report.setRiskLevel(RiskLevel.EARLY_ONSET);
        report.setPatientAge(30);

        assertNotNull(report.hashCode());
    }

    @Test
    public void testEquals()
    {
        Report report = new Report();
        report.setRiskLevel(RiskLevel.EARLY_ONSET);
        report.setPatientAge(30);

        Report report2 = new Report();
        report2.setRiskLevel(RiskLevel.EARLY_ONSET);
        report2.setPatientAge(30);

        Report report3 = new Report();
        report3.setRiskLevel(RiskLevel.NONE);
        report3.setPatientAge(30);


        assertTrue(report.equals(report));

        assertTrue(report.equals(report2));

        report2 = null;

        assertFalse(report.equals(report2));

        assertFalse((new Report().equals(report)));

        assertFalse(report.equals(report3));

        report3.setRiskLevel(RiskLevel.EARLY_ONSET);

        report3.setPatientAge(22);

        assertFalse(report.equals(report3));
    }

    @Test
    public void testToString()
    {
        Report report = new Report();
        report.setRiskLevel(RiskLevel.EARLY_ONSET);
        report.setPatientAge(30);

        assertNotNull(report.toString());
    }

}
