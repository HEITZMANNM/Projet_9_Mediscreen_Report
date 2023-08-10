package com.Mediscreen.AppReport.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum RiskFactor {

    HEMOGLOBIN_A1C("hemoglobine a1c"),
    MICROALBUMIN("microalbumine"),
    HEIGHT("taille"),
    WEIGHT("poids"),
    SMOKER("fumeur"),
    ABNORMAL("anormal"),
    CHOLESTEROL("cholesterol"),
    RELAPSE("rechute"),
    DIZZINESS("vertige"),
    REACTION("reaction"),
    ANTIBODIES("anticorps");

    private String factorsInFrench;

    private List<RiskFactor> allFactors = Arrays.asList(RiskFactor.values());

    public List<RiskFactor> getAllFactors(){
        return allFactors;
    }

    private RiskFactor (String factorsInFrench)
    {
        this.factorsInFrench=factorsInFrench;
    }

}
