package com.example.staffSeatsBackend.service.ifs;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.vo.BasicRes;

public interface EmployeeService {
	/**
	 * 新增員工
	 **/
	public BasicRes create(Employee employee);
}
