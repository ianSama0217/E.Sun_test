package com.example.staffSeatsBackend.vo;

public class CreateFloorReq {

	private String floor;

	private String seat;

	public CreateFloorReq() {
		super();
	}

	public CreateFloorReq(String floor, String seat) {
		super();
		this.floor = floor;
		this.seat = seat;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

}
