package com.mangesh.service;

import java.util.List;
import java.util.Map;

import com.mangesh.model.ShipmentType;

public interface IShipmentTypeService {

	public Integer saveShipmentType(ShipmentType shipmentType);

	public List<ShipmentType> getAllShipmentType();

	public void deleteShipmentById(Integer shipId);

	public ShipmentType getOneShipmentType(Integer shipId);

	public Integer updateOneShipmentType(ShipmentType shipmentType);

	boolean isShipmentTypeCodeCount(String shipmentCode);

	List<Object[]> generateShipmentModeAndCount();
	
	Map<Integer,String> getIdAndShipmentCodeByUsingEnableShipment(String enable);
}
