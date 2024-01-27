package com.example.staffSeatsBackend.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.entity.SeatingChart;
import com.example.staffSeatsBackend.repository.EmployeeDao;
import com.example.staffSeatsBackend.repository.SeatingChartDao;
import com.example.staffSeatsBackend.service.ifs.SeatingChartService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;
import com.example.staffSeatsBackend.vo.GetSeatInfoRes;
import com.example.staffSeatsBackend.vo.InsertUserReq;
import com.example.staffSeatsBackend.vo.SeatEmpVo;

import jakarta.transaction.Transactional;

@Service
public class SeatingChartServiceImpl implements SeatingChartService {

	private Logger logger = LoggerFactory.getLogger(SeatingChartServiceImpl.class);

	@Autowired
	private SeatingChartDao seatingChartDao;

	@Autowired
	private EmployeeDao employeeDao;

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

	@Override
	public GetSeatInfoRes getSeatInfo(String id) {
		if (!seatingChartDao.existsById(id)) {
			return new GetSeatInfoRes(RtnMsg.SEAT_ID_NOT_FOUND);
		}

		// 存放座位及員工資料
		SeatEmpVo seatEmpVo = new SeatEmpVo();

		SeatingChart seatInfo = seatingChartDao.findById(id).get();
		// 檢查資料內容
		if (seatInfo == null) {
			return new GetSeatInfoRes(RtnMsg.SEAT_DATA_ERROR);
		}

		// 加入座位資訊
		seatEmpVo.setSeatingChart(seatInfo);

		// 確認座位是否有使用者
		if (!employeeDao.existsByFloorSeatSeq(id)) {
			// 回傳座位資訊
			return new GetSeatInfoRes(RtnMsg.GET_SEAT_INFO_SUCCESSFUL, seatEmpVo);
		}

		Employee employee = employeeDao.findByFloorSeatSeq(id);
		seatEmpVo.setEmployee(employee);

		// 回傳座位+員工資訊
		return new GetSeatInfoRes(RtnMsg.GET_SEAT_INFO_SUCCESSFUL, seatEmpVo);

	}

	@Override
	public GetSeatInfoRes getAllInfo() {
		// 存放座位及員工資料
		List<SeatEmpVo> seatEmpVoList = new ArrayList<>();

		// 顯示所有座位
		List<SeatingChart> seatingChartList = seatingChartDao.findAll();

		// 若座位沒有資料 => 給一個空List
		seatingChartList = seatingChartList.size() != 0 ? seatingChartList : Collections.emptyList();

		for (SeatingChart seat : seatingChartList) {
			SeatEmpVo seatEmpVo = new SeatEmpVo();

			seatEmpVo.setSeatingChart(seat);
			// 檢查指定座位是否有員工使用
			if (!employeeDao.existsByFloorSeatSeq(seat.getFloorSeatSeq())) {
				seatEmpVoList.add(seatEmpVo);
			} else {
				// 若有員工使用 => 加入員工資料
				Employee employee = employeeDao.findByFloorSeatSeq(seat.getFloorSeatSeq());
				seatEmpVo.setEmployee(employee);
				seatEmpVoList.add(seatEmpVo);
			}
		}

		return new GetSeatInfoRes(RtnMsg.GET_SEAT_INFO_SUCCESSFUL, seatEmpVoList);
	}

	@Transactional
	@Override
	public BasicRes insertUser(InsertUserReq req) {
		String empId = req.getEmpId();
		String seatId = req.getSeatId();

		// 檢查參數
		if (!StringUtils.hasText(empId) || //
				!StringUtils.hasText(seatId)) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		if (!employeeDao.existsById(empId)) {
			return new BasicRes(RtnMsg.EMP_ID_NOT_FOUND);
		}

		if (!seatingChartDao.existsById(seatId)) {
			return new BasicRes(RtnMsg.SEAT_ID_NOT_FOUND);
		}

		// 檢查座位是否有使用者
		SeatingChart seatingChart = seatingChartDao.findById(seatId).get();
		if (seatingChart.getState().matches("已佔用")) {
			return new BasicRes(RtnMsg.SEAT_HAD_USER);
		}

		Employee employee = employeeDao.findById(empId).get();
		// 檢查員工是否已經有座位
		if (StringUtils.hasText(employee.getFloorSeatSeq())) {
			return new BasicRes(RtnMsg.EMPLOYEE_HAD_SEAT);
		}

		try {
			employeeDao.updateSeatId(empId, seatId);
			seatingChartDao.updateState(seatId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnMsg.INSERT_USER_FAILED);
		}

		return new BasicRes(RtnMsg.INSERT_USER_SUCCESSFUL);
	}

	@Transactional
	@Override
	public BasicRes clearUser(String seatId) {

		// 檢查參數
		if (!StringUtils.hasText(seatId)) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		if (!seatingChartDao.existsById(seatId)) {
			return new BasicRes(RtnMsg.SEAT_ID_NOT_FOUND);
		}

		// 取得員工id
		Employee employee = employeeDao.findByFloorSeatSeq(seatId);

		if (employee == null) {
			return new BasicRes(RtnMsg.EMPLOYEE_DATA_ERROR);
		}
		String empId = employee.getEmpId();

		try {
			employeeDao.clearSeatId(empId);
			seatingChartDao.clearState(seatId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnMsg.CLEAR_USER_FAILED);
		}

		return new BasicRes(RtnMsg.CLEAR_USER_SUCCESSFUL);
	}

}
