package com.mangesh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mangesh.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {

	@Query("SELECT ordType, COUNT(ordType) FROM OrderMethod GROUP BY ordType")
	List<Object[]> generateOrderTypeChartAndCount();

	@Query("SELECT ordId,ordCode FROM OrderMethod WHERE ordMode=:ordMode")
	List<Object[]> getOrderMethodOrdIdAndOrdCodeByUsingMode(String ordMode);
}
