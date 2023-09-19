package com.openclassrooms.safetyNet.dto;

import java.util.List;

public class ResidentInfoMedicalRecordsDTO {
    private String LastName;
    private String phone;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;

    public ResidentInfoMedicalRecordsDTO(String lastName, String phone, Integer age, List<String> medications, List<String> allergies) {
        LastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public ResidentInfoMedicalRecordsDTO(){}

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getMedications() {
        return medications;
    }

    public void setMedications(List<String> medications) {
        this.medications = medications;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }


    @Override
    public String toString() {
        return "ResidentInfoMedicalRecordsDTO{" +
                "LastName='" + LastName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", medications=" + medications +
                ", allergies=" + allergies +
                '}';
    }
}
