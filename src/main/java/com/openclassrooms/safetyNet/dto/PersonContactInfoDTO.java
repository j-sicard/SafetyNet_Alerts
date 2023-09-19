package com.openclassrooms.safetyNet.dto;

import java.io.Serializable;

public class PersonContactInfoDTO implements Serializable {

    /**
     * UUID
     */
    private static final long serialVersionUID = -4815539740390384741L;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String age;

    public PersonContactInfoDTO(String firstName, String lastName, String address, String phone, String age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
