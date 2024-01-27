package com.example.staffSeatsBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.staffSeatsBackend.entity.Employee;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String> {

	// 確認信箱是否存在
	public boolean existsByEmail(String email);

	// 新增員工資料
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "INSERT INTO employee(EMP_ID, NAME, EMAIL)"//
			+ " VALUES (:id, :name, :email)", nativeQuery = true)
	public int insert(@Param("id") String empId, //
			@Param("name") String name, //
			@Param("email") String email);

	// 更新座位編號
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE employee SET FLOOR_SEAT_SEQ = :seatId" //
			+ " WHERE EMP_ID = :empId", nativeQuery = true)
	public int updateSeatId(@Param("empId") String empId, //
			@Param("seatId") String seatId);
}
