package com.example.staffSeatsBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.staffSeatsBackend.entity.SeatingChart;

import jakarta.transaction.Transactional;

@Repository
public interface SeatingChartDao extends JpaRepository<SeatingChart, String> {

	public boolean existsByFloorNo(String floor);

	// 更新座位狀態(已佔用)
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE seatingchart SET STATE = '已佔用'"//
			+ " WHERE FLOOR_SEAT_SEQ = :id", nativeQuery = true)
	public int updateState(@Param("id") String seatId);

	// 更新座位狀態(空位)
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE seatingchart SET STATE = '空位'"//
			+ " WHERE FLOOR_SEAT_SEQ = :id", nativeQuery = true)
	public int clearState(@Param("id") String seatId);
}
