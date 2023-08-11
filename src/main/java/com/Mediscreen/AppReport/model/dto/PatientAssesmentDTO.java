package com.Mediscreen.AppReport.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class PatientAssesmentDTO {

    private String id;

    private int patientId;

    private Date date;

    private String assessment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientAssesmentDTO that = (PatientAssesmentDTO) o;
        return id == that.id && Objects.equals(patientId, that.patientId) && Objects.equals(date, that.date)
                && Objects.equals(assessment, that.assessment) ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, patientId, date, assessment);
    }
}
