package com.example.staffSeatsBackend.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.entity.SeatingChart;
import com.example.staffSeatsBackend.repository.EmployeeDao;
import com.example.staffSeatsBackend.repository.SeatingChartDao;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.GetInfoRes;

import jakarta.transaction.Transactional;

@SpringBootTest
class EmployeeServiceImplTest {

	@Autowired
	private EmployeeServiceImpl service;

	@MockBean
	private EmployeeDao employeeDao;

	@MockBean
	private SeatingChartDao seatingChartDao;

	@BeforeEach
	public void beforeEach() {
		// 沒座位的員工
		Employee mockEmp = new Employee();
		mockEmp.setEmpId("12345");
		mockEmp.setName("ian");
		mockEmp.setEmail("ian@gmail.com");

		// 有座位的員工
		Employee mockEmp2 = new Employee();
		mockEmp2.setEmpId("54321");
		mockEmp2.setName("jack");
		mockEmp2.setEmail("jack@gmail.com");
		mockEmp2.setFloorSeatSeq("1F1");

		SeatingChart mockSeat = new SeatingChart();
		mockSeat.setFloorNo("1F1");
		mockSeat.setFloorNo("1");
		mockSeat.setSeatNo("1");
		mockSeat.setState("已佔用");

		Mockito.when(employeeDao.existsById("12345")).thenReturn(true);
		Mockito.when(employeeDao.existsById("54321")).thenReturn(true);
		Mockito.when(employeeDao.existsById("11111")).thenReturn(true);
		Mockito.when(employeeDao.existsById("00000")).thenReturn(false);
		Mockito.when(employeeDao.existsByEmail("123@gmail.com")).thenReturn(true);
		Mockito.when(employeeDao.existsByEmail("321@gmail.com")).thenReturn(false);
		Mockito.when(employeeDao.findById("12345")).thenReturn(Optional.of(mockEmp));
		Mockito.when(employeeDao.findById("54321")).thenReturn(Optional.of(mockEmp2));
		Mockito.when(employeeDao.findById("11111")).thenReturn(Optional.empty());
	}

	@Transactional
	@Test
	public void createTest() {

		Mockito.when(employeeDao.insert(any(), any(), any())).thenReturn(1);

		// PARAM ERROR
		Employee emp = new Employee();
		emp.setName("ian");

		BasicRes res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "參數錯誤");

		// EMAIL FORMAT ERROR
		emp.setEmpId("12345");
		emp.setEmail("ian20000217");

		res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "信箱格式錯誤");

		// EMPID FORMAT ERROR
		emp.setEmpId("ian0217");
		emp.setEmail("ian20000217@gmail.com");

		res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "員工編號格式錯誤");

		// ID 重複
		emp.setEmpId("12345");

		res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "員工編號已存在");

		// EMAIL 重複
		emp.setEmpId("24001");
		emp.setEmail("123@gmail.com");

		res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "信箱已存在");

		// SUCCESS
		emp.setEmail("ian20000217@gmail.com");

		res = service.create(emp);
		assertEquals(res.getRtnMsg().getMessage(), "新增員工成功");
	}

	@Transactional
	@Test
	public void deleteTest() {
		// ID NOT NOUND
		BasicRes res = service.delete("00000");

		assertEquals(res.getRtnMsg().getMessage(), "找不到員工編號");

		// SUCCESS(刪沒座位的)
		res = service.delete("12345");

		assertEquals(res.getRtnMsg().getMessage(), "刪除員工成功");

		// SUCCESS(刪有座位的)
		res = service.delete("54321");

		Mockito.when(seatingChartDao.clearState(any())).thenReturn(1);
		assertEquals(res.getRtnMsg().getMessage(), "刪除員工成功");
	}

	@Test
	public void getInfoTest() {
		// EMP ID NOT FOUND
		GetInfoRes res = service.getInfo("00000");

		assertEquals(res.getRtnMsg().getMessage(), "找不到員工編號");

		//TODO EMP DATA ERROR
//		res = service.getInfo("11111");
//
//		assertEquals(res.getRtnMsg().getMessage(), "員工資料異常");
		
		//SUCCESS
		res = service.getInfo("12345");

		assertEquals(res.getRtnMsg().getMessage(), "取得員工資料");
		assertEquals(res.getEmployee().getEmpId(), "12345");
	}
}