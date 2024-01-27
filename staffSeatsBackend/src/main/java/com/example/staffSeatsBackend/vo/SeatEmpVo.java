package com.example.staffSeatsBackend.vo;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.entity.SeatingChart;

public class SeatEmpVo {

	private SeatingChart seatingChart;

	private Employee employee;

	public SeatEmpVo() {
		super();
	}

	public SeatEmpVo(SeatingChart seatingChart) {
		super();
		this.seatingChart = seatingChart;
	}

	public SeatEmpVo(SeatingChart seatingChart, Employee employee) {
		super();
		this.seatingChart = seatingChart;
		this.employee = employee;
	}

	public SeatingChart getSeatingChart() {
		return seatingChart;
	}

	public void setSeatingChart(SeatingChart seatingChart) {
		this.seatingChart = seatingChart;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
