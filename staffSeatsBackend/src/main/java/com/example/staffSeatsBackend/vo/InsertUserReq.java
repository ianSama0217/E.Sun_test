package com.example.staffSeatsBackend.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InsertUserReq {

	@JsonProperty("emp_id")
	private String empId;

	@JsonProperty("seat_id")
	private String seatId;

	public InsertUserReq() {
		super();
	}

	public InsertUserReq(String empId, String seatId) {
		super();
		this.empId = empId;
		this.seatId = seatId;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

}
