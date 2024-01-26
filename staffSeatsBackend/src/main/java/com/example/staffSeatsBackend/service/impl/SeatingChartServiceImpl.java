package com.example.staffSeatsBackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.SeatingChart;
import com.example.staffSeatsBackend.repository.SeatingChartDao;
import com.example.staffSeatsBackend.service.ifs.SeatingChartService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;

@Service
public class SeatingChartServiceImpl implements SeatingChartService {

	private Logger logger = LoggerFactory.getLogger(SeatingChartServiceImpl.class);

	@Autowired
	private SeatingChartDao seatingChartDao;

	@Override
	public BasicRes addFloor(CreateFloorReq req) {
		String floor = req.getFloor();
		int seats = Integer.parseInt(req.getSeat());

		// 檢查參數
		if (!StringUtils.hasText(floor) || //
				seats <= 0) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		// 檢查樓層是否存在
		if (seatingChartDao.existsByFloorNo(floor)) {
			return new BasicRes(RtnMsg.FLOOR_IS_EXIST);
		}

		// 建立List存放新增的資料
		List<SeatingChart> seatingChartsList = new ArrayList<>();

		for (int i = 1; i <= seats; i++) {
			SeatingChart seatingChart = new SeatingChart();
			seatingChart.setFloorSeatSeq(floor + "F" + String.valueOf(i));
			seatingChart.setFloorNo(floor);
			seatingChart.setSeatNo(String.valueOf(i));
			seatingChart.setState("空位");
			seatingChartsList.add(seatingChart);
		}

		try {
			seatingChartDao.saveAll(seatingChartsList);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnMsg.CREATE_FLOOR_FAILED);
		}

		return new BasicRes(RtnMsg.CREATE_FLOOR_SUCCESSFUL);
	}

}
