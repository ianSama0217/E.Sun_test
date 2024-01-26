package com.example.staffSeatsBackend.vo;

import java.util.List;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.Employee;

public class GetInfoRes {
	private RtnMsg rtnMsg;

	private Employee employee;

	private List<Employee> employeeList;

	public GetInfoRes() {
		super();
	}

	public GetInfoRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public GetInfoRes(RtnMsg rtnMsg, Employee employee) {
		super();
		this.rtnMsg = rtnMsg;
		this.employee = employee;
	}

	public GetInfoRes(RtnMsg rtnMsg, List<Employee> employeeList) {
		super();
		this.rtnMsg = rtnMsg;
		this.employeeList = employeeList;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

	public Employee getEmployee() {
		return employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

}
