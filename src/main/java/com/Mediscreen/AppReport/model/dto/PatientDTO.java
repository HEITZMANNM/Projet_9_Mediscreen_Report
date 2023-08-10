package com.Mediscreen.AppReport.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PatientDTO {


    private int id;


    private String firstName;


    private String lastName;


    private Date birthDate;


    private String gender;


    private String address;


    private String phoneNumber;
}
