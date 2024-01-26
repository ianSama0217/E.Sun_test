package com.example.staffSeatsBackend.vo;

import com.example.staffSeatsBackend.constants.RtnMsg;

public class BasicRes {

	private RtnMsg rtnMsg;

	public BasicRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

}
