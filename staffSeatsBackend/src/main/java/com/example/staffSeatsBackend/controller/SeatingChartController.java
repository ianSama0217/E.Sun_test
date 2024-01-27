package com.example.staffSeatsBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.staffSeatsBackend.constants.RtnMsg;
import com.example.staffSeatsBackend.service.ifs.SeatingChartService;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.CreateFloorReq;
import com.example.staffSeatsBackend.vo.GetSeatInfoRes;
import com.example.staffSeatsBackend.vo.InsertUserReq;

@CrossOrigin
@RestController
public class SeatingChartController {

	@Autowired
	private SeatingChartService service;

	// 新增樓層
	@PostMapping(value = "/floor")
	public ResponseEntity<BasicRes> addFloor(@RequestBody CreateFloorReq req) {
		BasicRes res = service.addFloor(req);

		if (!res.getRtnMsg().equals(RtnMsg.CREATE_FLOOR_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(res);
	}

	// 顯示指定座位資訊
	@GetMapping(value = "/seat/{id}")
	public ResponseEntity<GetSeatInfoRes> getSeatInfo(//
			@PathVariable(name = "id") String id) {
		GetSeatInfoRes res = service.getSeatInfo(id);

		if (!res.getRtnMsg().equals(RtnMsg.GET_SEAT_INFO_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	// 顯示所有座位
	@GetMapping(value = "/seat")
	public ResponseEntity<GetSeatInfoRes> getAllInfo() {
		GetSeatInfoRes res = service.getAllInfo();

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	// 新增使用者到座位
	@PatchMapping(value = "/seat/insert")
	public ResponseEntity<BasicRes> insertUser(@RequestBody InsertUserReq req) {
		BasicRes res = service.insertUser(req);

		if (!res.getRtnMsg().equals(RtnMsg.INSERT_USER_SUCCESSFUL)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

}
