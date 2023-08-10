package com.Mediscreen.AppReport;

import com.Mediscreen.AppReport.service.ReportService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
