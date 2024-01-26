package com.example.staffSeatsBackend.service.ifs;

import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;
import com.example.staffSeatsBackend.vo.GetSeatInfoRes;

public interface SeatingChartService {

	/**
	 * 新增樓層
	 **/
	public BasicRes addFloor(CreateFloorReq req);

	/**
	 * TODO 包含座位上有人的員工資料
	 *  顯示座位資訊
	 **/
	public GetSeatInfoRes getSeatInfo(String id);

	/**
	 * TODO 依照樓層排序
	 * 顯示所有座位
	 **/
	public GetSeatInfoRes getAllInfo();
}
