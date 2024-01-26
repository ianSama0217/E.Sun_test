package com.example.staffSeatsBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.staffSeatsBackend.entity.SeatingChart;

@Repository
public interface SeatingChartDao extends JpaRepository<SeatingChart, String> {

	public boolean existsByFloorNo(String floor);
}
