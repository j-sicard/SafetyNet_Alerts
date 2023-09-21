package com.openclassrooms.safetyNet.dto;

import java.util.List;

public class ChildrenAdultDTO {
    List<PersonInfoForChildAlertDTO> children;
    List<PersonInfoForChildAlertDTO> adult;

    public ChildrenAdultDTO(List<PersonInfoForChildAlertDTO> children, List<PersonInfoForChildAlertDTO> adult) {
        this.children = children;
        this.adult = adult;
    }

    public ChildrenAdultDTO(){}

    public List<PersonInfoForChildAlertDTO> getChildren() {
        return children;
    }

    public void setChildren(List<PersonInfoForChildAlertDTO> children) {
        this.children = children;
    }

    public List<PersonInfoForChildAlertDTO> getAdult() {
        return adult;
    }

    public void setAdult(List<PersonInfoForChildAlertDTO> adult) {
        this.adult = adult;
    }

    @Override
    public String toString() {
        return "ChildrenAdultDTO{" +
                "children=" + children +
                ", adult=" + adult +
                '}';
    }
}
