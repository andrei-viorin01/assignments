package com.fis.assignment.model;

/**
* Enum representing the available statuses (ex. Expired).
*
* @author Andrei Almasanu
*/

public enum Status {
	ACTIVE("Active"), CANCELLED("Cancelled"), EXPIRED("Expired");

	private String status;

	Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
