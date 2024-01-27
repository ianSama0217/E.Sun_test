package com.example.staffSeatsBackend.vo;

public class InsertUserReq {

	private String empId;

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
