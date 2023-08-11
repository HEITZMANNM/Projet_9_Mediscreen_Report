package com.Mediscreen.AppReport.model;


public enum RiskFactor {

    HEMOGLOBIN_A1C("hemoglobine a1c"),
    MICROALBUMIN("microalbumine"),
    HEIGHT("taille"),
    WEIGHT("poids"),
    SMOKER("fume"),
    ABNORMAL("anormal"),
    CHOLESTEROL("cholestérol"),
    RELAPSE("rechute"),
    DIZZINESS("vertige"),
    REACTION("reaction"),
    ANTIBODIES("anticorps");

    private final String factorsInFrench;


     RiskFactor (String factorsInFrench)
    {
        this.factorsInFrench=factorsInFrench;
    }

    public String getFactorInFrench()
    {
        return factorsInFrench;
    }

}
