package com.Mediscreen.AppReport.model;

import lombok.Getter;

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

    private RiskFactor (String factorsInFrench)
    {
        this.factorsInFrench=factorsInFrench;
    }

}
