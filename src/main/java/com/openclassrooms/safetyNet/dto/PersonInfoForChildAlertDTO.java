package com.openclassrooms.safetyNet.dto;

public class PersonInfoForChildAlertDTO {
    private String FirstName;
    private String LastName;
    private Integer age;

    public PersonInfoForChildAlertDTO(String firstName, String lastName, Integer age) {
        FirstName = firstName;
        LastName = lastName;
        this.age = age;
    }

    public PersonInfoForChildAlertDTO(){}

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonInfoForChildAlertDTO{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", age=" + age +
                '}';
    }
}
