package com.openclassrooms.safetyNet.dto;

import java.io.Serializable;
import java.util.List;

public class ResidentAndAgesDTO implements Serializable{

    /**
     * UUID
     */
    private static final long serialVersionUID = -6700907461068655566L;

    private List<ResidentInfoDTO> residents;

    private int nbAdult;

    private int nbChildren;

    public ResidentAndAgesDTO(List<ResidentInfoDTO> residents, int nbAdult, int nbChildren) {
        super();
        this.residents = residents;
        this.nbAdult = nbAdult;
        this.nbChildren = nbChildren;
    }

    public List<ResidentInfoDTO> getResidents() {
        return residents;
    }

    public void setResidents(List<ResidentInfoDTO> residents) {
        this.residents = residents;
    }

    public int getNbAdult() {
        return nbAdult;
    }

    public void setNbAdult(int nbAdult) {
        this.nbAdult = nbAdult;
    }

    public int getNbChildren() {
        return nbChildren;
    }

    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }

}
