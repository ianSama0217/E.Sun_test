package com.example.staffSeatsBackend.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.staffSeatsBackend.entity.Employee;
import com.example.staffSeatsBackend.service.ifs.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

//TODO create, delete, findAll
@AutoConfigureMockMvc
@SpringBootTest
class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@SpyBean
	private EmployeeService service;
	
	@Test
	public void createTest() throws Exception{
		Employee employee = new Employee();
		employee.setEmpId("66666");
		employee.setName("ian");
		employee.setEmail("ian@gmail.com");
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonEmp = mapper.writeValueAsString(employee);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/employee")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonEmp);
		
		mockMvc.perform(requestBuilder)
				.andExpect(status().is(201));
	}
	
	@Test
	public void getInfoTest() throws Exception{
		// http status 200
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/employee/12345");

		mockMvc.perform(requestBuilder)
				.andExpect(status().is(200));

		// http status 400
		requestBuilder = MockMvcRequestBuilders
				.get("/employee/00000");
		
		mockMvc.perform(requestBuilder)
				.andExpect(status().is(400));
	}
}