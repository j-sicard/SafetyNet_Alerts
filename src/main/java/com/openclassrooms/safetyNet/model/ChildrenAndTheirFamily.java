package com.openclassrooms.safetyNet.model;

import java.util.List;

public class ChildrenAndTheirFamily {
    private List<ChildInfo> childrenInfo;
    private List<FamilyInformation> family;
    public ChildrenAndTheirFamily() {
    }

    public ChildrenAndTheirFamily(List<ChildInfo> childrenInfo, List<FamilyInformation> family) {
        this.childrenInfo = childrenInfo;
        this.family = family;
    }

    public List<ChildInfo> getChildrenInfo() {
        return childrenInfo;
    }

    public void setChildrenInfo(List<ChildInfo> childrenInfo) {
        this.childrenInfo = childrenInfo;
    }

    public List<FamilyInformation> getFamily() {
        return family;
    }

    public void setFamily(List<FamilyInformation> family) {
        this.family = family;
    }

    @Override
    public String toString() {
        return "ChildrenAndTheirFamily{" +
                "childrenInfo=" + childrenInfo +
                ", family=" + family +
                '}';
    }
}
