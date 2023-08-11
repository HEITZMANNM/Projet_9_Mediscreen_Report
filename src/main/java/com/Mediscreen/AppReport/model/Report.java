package com.Mediscreen.AppReport.model;

import lombok.Data;

import java.util.Objects;


@Data
public class Report {


    int patientAge;

    RiskLevel riskLevel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report that = (Report) o;
        return patientAge == that.patientAge && Objects.equals(riskLevel, that.riskLevel) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientAge, riskLevel);
    }

}
