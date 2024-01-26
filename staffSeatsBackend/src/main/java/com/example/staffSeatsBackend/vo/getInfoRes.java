package com.example.staffSeatsBackend.vo;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.Employee;

public class getInfoRes {
	private RtnMsg rtnMsg;

	private Employee employee;

	public getInfoRes() {
		super();
	}

	public getInfoRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public getInfoRes(RtnMsg rtnMsg, Employee employee) {
		super();
		this.rtnMsg = rtnMsg;
		this.employee = employee;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

	public Employee getEmployee() {
		return employee;
	}

}
