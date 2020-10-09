package com.mangesh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mangesh.model.ShipmentType;

public interface ShipmentRepository extends JpaRepository<ShipmentType, Integer> {

	@Query("SELECT COUNT(shipmentCode) FROM ShipmentType WHERE shipmentCode=:shipmentCode")
	Integer getShipmentTypeCodeCount(String shipmentCode);

	@Query("SELECT shipmentId,shipmentMode FROM ShipmentType WHERE shipmentId>=:id")
	Object[] moreThanId(Integer id);

	@Query("SELECT shipmentMode,COUNT(shipmentMode) FROM ShipmentType GROUP BY shipmentMode")
	List<Object[]> generateShipmentModeAndCount();

	@Query("SELECT shipmentId,shipmentCode FROM ShipmentType WHERE enableShipment=:enableShipment")
	List<Object[]> getIdAndShipmentCodeByUsingEnableShipment(String enableShipment);
}
