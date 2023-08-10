package com.Mediscreen.AppReport.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientAssesmentDTO {

    private String id;

    private int patientId;

    private Date date;

    private String assessment;
}
