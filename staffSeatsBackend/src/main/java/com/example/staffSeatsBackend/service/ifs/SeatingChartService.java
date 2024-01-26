package com.example.staffSeatsBackend.service.ifs;

import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;

public interface SeatingChartService {

	/**
	 * 新增樓層(預設四個座位)
	 **/
	public BasicRes addFloor(CreateFloorReq req);
}
