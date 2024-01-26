package com.example.staffSeatsBackend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seatingchart")
public class SeatingChart {

	// 座位編號
	@Id
	@Column(name = "FLOOR_SEAT_SEQ")
	@JsonProperty("floor_seat_seq")
	private String floorSeatSeq;

	// 樓層編號
	@Column(name = "FLOOR_NO")
	@JsonProperty("floor_no")
	private String floorNo;

	// 座位編號
	@Column(name = "SEAT_NO")
	@JsonProperty("seat_no")
	private String seatNo;

	// 座位狀態(空位,已占用)
	@Column(name = "STATE")
	private String state;

	public SeatingChart() {
		super();
	}

	public String getFloorSeatSeq() {
		return floorSeatSeq;
	}

	public void setFloorSeatSeq(String floorSeatSeq) {
		this.floorSeatSeq = floorSeatSeq;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
