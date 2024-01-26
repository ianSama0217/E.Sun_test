package com.example.staffSeatsBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.service.ifs.EmployeeService;
import com.example.staffSeatsBackend.vo.BasicRes;

@CrossOrigin
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	// 新增員工
	@PostMapping(value = "/employee")
	public ResponseEntity<BasicRes> create(@RequestBody Employee employee) {
		BasicRes res = service.create(employee);

		if (res == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
}
