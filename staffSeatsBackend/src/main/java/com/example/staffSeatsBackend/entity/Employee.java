package com.example.staffSeatsBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {

	// 員工編號
	@Id
	@Column(name = "EMP_ID")
	@JsonProperty("emp_id")
	private String empId;

	// 員工姓名
	@Column(name = "NAME")
	private String name;

	// 員工信箱
	@Column(name = "EMAIL")
	private String email;

	// 員工座位編號
	@Column(name = "FLOOR_SEAT_SEQ")
	@JsonProperty("floor_seat_seq")
	private String floorSeatSeq;

	public Employee() {
		super();
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(String floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

}
