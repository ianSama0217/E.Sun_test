package com.example.staffSeatsBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.service.ifs.EmployeeService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.GetInfoRes;

@CrossOrigin
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	// 新增員工
	@PostMapping(value = "/employee")
	public ResponseEntity<BasicRes> create(@RequestBody Employee employee) {
		BasicRes res = service.create(employee);

		if (!res.getRtnMsg().equals(RtnMsg.CREATE_EMPLOYEE_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	// 刪除員工
	@DeleteMapping(value = "/employee/{id}")
	public ResponseEntity<BasicRes> delete(//
			@PathVariable(name = "id") String id) {

		BasicRes res = service.delete(id);

		if (!res.getRtnMsg().equals(RtnMsg.DELETE_EMPLOYEE_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	// 查詢員工資料
	@GetMapping(value = "/employee/{id}")
	public ResponseEntity<GetInfoRes> getInfo(//
			@PathVariable(name = "id") String id) {

		GetInfoRes res = service.getInfo(id);

		if (!res.getRtnMsg().equals(RtnMsg.GET_EMPLOYEE_INFO_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
	
	//顯示所有員工
	@GetMapping(value = "/employee")
	public ResponseEntity<GetInfoRes> getAllInfo() {
		GetInfoRes res = service.getAllInfo();
		
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}
}
