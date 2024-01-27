package com.example.staffSeatsBackend.service.ifs;

import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;
import com.example.staffSeatsBackend.vo.GetSeatInfoRes;
import com.example.staffSeatsBackend.vo.InsertUserReq;

public interface SeatingChartService {

	/**
	 * 新增樓層
	 **/
	public BasicRes addFloor(CreateFloorReq req);

	/**
	 * 設定座位給user使用
	 **/
	public BasicRes insertUser(InsertUserReq req);
	
	/**
	 * 清除座位的user(參數:floor_seat_seq)
	 **/
	public BasicRes clearUser(String seatId);

	/**
	 * 顯示座位資訊(單筆)
	 **/
	public GetSeatInfoRes getSeatInfo(String id);

	/**
	 * 顯示所有座位資訊
	 **/
	public GetSeatInfoRes getAllInfo();
}
