package com.example.staffSeatsBackend.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.repository.EmployeeDao;
import com.example.staffSeatsBackend.repository.SeatingChartDao;
import com.example.staffSeatsBackend.service.ifs.EmployeeService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.GetInfoRes;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private SeatingChartDao seatingChartDao;

	@Override
	public BasicRes create(Employee employee) {
		String id = employee.getEmpId();
		String name = employee.getName();
		String email = employee.getEmail();

		// 檢查參數
		if (!StringUtils.hasText(id) || //
				!StringUtils.hasText(name) || //
				!StringUtils.hasText(email)) {
			return new BasicRes(RtnMsg.PARAM_ERROR);
		}

		// 檢查員工id格式
		if (!id.matches("\\d{5}")) {
			return new BasicRes(RtnMsg.EMP_ID_FORMAT_ERROR);
		}

		// 檢查信箱格式
		if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
			return new BasicRes(RtnMsg.EMAIL_FORMAT_ERROR);
		}

		// 檢查員工id是否存在
		if (employeeDao.existsById(id)) {
			return new BasicRes(RtnMsg.EMP_ID_IS_EXIST);
		}

		// 檢查信箱是否存在
		if (employeeDao.existsByEmail(email)) {
			return new BasicRes(RtnMsg.EMAIL_IS_EXIST);
		}

		try {
			employeeDao.insert(id, name, email);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnMsg.CREATE_EMPLOYEE_FAILED);
		}

		return new BasicRes(RtnMsg.CREATE_EMPLOYEE_SUCCESSFUL);
	}

	@Transactional
	@Override
	public BasicRes delete(String id) {
		if (!employeeDao.existsById(id)) {
			return new BasicRes(RtnMsg.EMP_ID_NOT_FOUND);
		}

		Employee employee = employeeDao.findById(id).get();

		// 檢查員工是否有座位
		if (employee.getFloorSeatSeq() != null) {
			String seatId = employee.getFloorSeatSeq();

			try {
				seatingChartDao.clearState(seatId);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return new BasicRes(RtnMsg.CLEAR_USER_FAILED);
			}
		}

		try {
			employeeDao.deleteById(id);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new BasicRes(RtnMsg.DELETE_EMPLOYEE_FAILED);
		}

		return new BasicRes(RtnMsg.DELETE_EMPLOYEE_SUCCESSFUL);
	}

	@Override
	public GetInfoRes getInfo(String id) {
		if (!employeeDao.existsById(id)) {
			return new GetInfoRes(RtnMsg.EMP_ID_NOT_FOUND);
		}

		Employee employee = employeeDao.findById(id).get();
		// 檢查取得資料
		if (employee == null) {
			return new GetInfoRes(RtnMsg.EMPLOYEE_DATA_ERROR);
		}

		return new GetInfoRes(RtnMsg.GET_EMPLOYEE_INFO_SUCCESSFUL, employee);
	}

	@Override
	public GetInfoRes getAllInfo() {
		List<Employee> employeeList = employeeDao.findAll();

		// 若員工沒有資料 => 給一個空List
		employeeList = employeeList.size() != 0 ? employeeList : Collections.emptyList();
		return new GetInfoRes(RtnMsg.GET_EMPLOYEE_INFO_SUCCESSFUL, employeeList);
	}

}
