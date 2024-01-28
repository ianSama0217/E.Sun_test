package com.example.staffSeatsBackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EmployeeDaoTest {

	@Autowired
	private EmployeeDao employeeDao;

	@Transactional
	@Test
	public void insertTest() {
		// 新增成功
		int res = employeeDao.insert("12345", "ian", "ian@gmail.com");
		assertEquals(res, 1);
	}

	@Transactional
	@Test
	public void updateSeatIdTest() {
		// 更新成功
		int res = employeeDao.updateSeatId("24002", "1F2");
		assertEquals(res, 1);
	}

	@Transactional
	@Test
	public void clearSeatIdTest() {
		// 更新成功
		int res = employeeDao.clearSeatId("24002");
		assertEquals(res, 1);
	}

}