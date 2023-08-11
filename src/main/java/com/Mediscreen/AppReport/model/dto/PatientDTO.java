package com.Mediscreen.AppReport.model.dto;

import lombok.Data;

import java.util.Date;
import java.util.Objects;

@Data
public class PatientDTO {


    private int id;

    private String firstName;

    private String lastName;

    private Date birthDate;

    private String gender;

    private String address;

    private String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO that = (PatientDTO) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthDate, that.birthDate) && Objects.equals(gender, that.gender) && Objects.equals(address, that.address)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, gender, address, phoneNumber);
    }
}
