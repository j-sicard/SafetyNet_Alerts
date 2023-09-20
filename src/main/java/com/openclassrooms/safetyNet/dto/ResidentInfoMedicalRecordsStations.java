package com.openclassrooms.safetyNet.dto;

import java.io.Serializable;
import java.util.List;

public class ResidentInfoMedicalRecordsStations implements Serializable {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = -6789795691899668756L;
	public List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords;
	public String stationNumbers;

	public ResidentInfoMedicalRecordsStations(List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords,
			String stationNumbers) {
		this.residentInfoMedicalRecords = residentInfoMedicalRecords;
		this.stationNumbers = stationNumbers;
	}

	public List<ResidentInfoMedicalRecordsDTO> getResidentInfoMedicalRecords() {
		return residentInfoMedicalRecords;
	}

	public void setResidentInfoMedicalRecords(List<ResidentInfoMedicalRecordsDTO> residentInfoMedicalRecords) {
		this.residentInfoMedicalRecords = residentInfoMedicalRecords;
	}

	public String getStationNumbers() {
		return stationNumbers;
	}

	public void setStationNumbers(String stationNumbers) {
		this.stationNumbers = stationNumbers;
	}

	@Override
	public String toString() {
		return "ResidentInfoMedicalRecordsStations{" +
				"residentInfoMedicalRecords=" + residentInfoMedicalRecords +
				", stationNumbers='" + stationNumbers + '\'' +
				'}';
	}
}
