package com.example.staffSeatsBackend.vo;

import java.util.List;

import com.example.staffSeatsBackend.constants.RtnMsg;

public class GetSeatInfoRes {
	private RtnMsg rtnMsg;

	private SeatEmpVo seatInfo;

	private List<SeatEmpVo> seatInfoList;

	public GetSeatInfoRes() {
		super();
	}

	public GetSeatInfoRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public GetSeatInfoRes(RtnMsg rtnMsg, List<SeatEmpVo> seatInfoList) {
		super();
		this.rtnMsg = rtnMsg;
		this.seatInfoList = seatInfoList;
	}

	public GetSeatInfoRes(RtnMsg rtnMsg, SeatEmpVo seatInfo) {
		super();
		this.rtnMsg = rtnMsg;
		this.seatInfo = seatInfo;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(RtnMsg rtnMsg) {
		this.rtnMsg = rtnMsg;
	}

	public SeatEmpVo getSeatInfo() {
		return seatInfo;
	}

	public void setSeatInfo(SeatEmpVo seatInfo) {
		this.seatInfo = seatInfo;
	}

	public List<SeatEmpVo> getSeatInfoList() {
		return seatInfoList;
	}

	public void setSeatInfoList(List<SeatEmpVo> seatInfoList) {
		this.seatInfoList = seatInfoList;
	}
}
