package com.example.staffSeatsBackend.service.ifs;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.vo.BasicRes;
import com.example.staffSeatsBackend.vo.getInfoRes;

public interface EmployeeService {
	/**
	 * 新增員工
	 **/
	public BasicRes create(Employee employee);

	/**
	 * 刪除員工
	 **/
	public BasicRes delete(String id);
	
	/**
	 * 查看員工資料
	 **/
	public getInfoRes getInfo(String id);
}
