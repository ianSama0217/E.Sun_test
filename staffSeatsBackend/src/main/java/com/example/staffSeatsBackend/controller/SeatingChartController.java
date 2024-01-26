package com.example.staffSeatsBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.staffSeatsBackend.service.ifs.SeatingChartService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;

@CrossOrigin
@RestController
public class SeatingChartController {

	@Autowired
	private SeatingChartService service;

	@PostMapping(value = "/floor")
	public ResponseEntity<BasicRes> addFloor(@RequestBody CreateFloorReq req) {
		BasicRes res = service.addFloor(req);

		if (res == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}
}
