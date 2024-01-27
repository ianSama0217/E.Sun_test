package com.example.staffSeatsBackend.vo;

import java.util.List;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.SeatingChart;

public class GetSeatInfoRes {
	private RtnMsg rtnMsg;

	private SeatingChart seatingChart;

	private List<SeatingChart> seatingChartList;

	public GetSeatInfoRes() {
		super();
	}

	public GetSeatInfoRes(RtnMsg rtnMsg) {
		super();
		this.rtnMsg = rtnMsg;
	}

	public GetSeatInfoRes(RtnMsg rtnMsg, SeatingChart seatingChart) {
		super();
		this.rtnMsg = rtnMsg;
		this.seatingChart = seatingChart;
	}

	public GetSeatInfoRes(RtnMsg rtnMsg, List<SeatingChart> seatingChartList) {
		super();
		this.rtnMsg = rtnMsg;
		this.seatingChartList = seatingChartList;
	}

	public RtnMsg getRtnMsg() {
		return rtnMsg;
	}

	public SeatingChart getSeatingChart() {
		return seatingChart;
	}

	public List<SeatingChart> getSeatingChartList() {
		return seatingChartList;
	}

}
