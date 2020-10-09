package com.mangesh.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.config.ListToMapConfig;
import com.mangesh.exception.ShipmentTypeNotFoundException;
import com.mangesh.model.ShipmentType;
import com.mangesh.repo.ShipmentRepository;
import com.mangesh.service.IShipmentTypeService;

@Service
public class IShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentRepository shipmentRepository;

	@Override
	public Integer saveShipmentType(ShipmentType shipmentType) {
		return shipmentRepository.save(shipmentType).getShipmentId();
	}

	@Override
	public List<ShipmentType> getAllShipmentType() {
		List<ShipmentType> list = shipmentRepository.findAll();
		list.sort((shipment1, shipment2) -> shipment1.getShipmentId() - shipment2.getShipmentId());
		return list;
	}

	@Override
	public void deleteShipmentById(Integer shipId) {
		Optional<ShipmentType> shipmentType = shipmentRepository.findById(shipId);
		if (shipmentType.isPresent()) {
			shipmentType.get().getShipmentId();
			shipmentRepository.deleteById(shipmentType.get().getShipmentId());
		} else {

		}

	}

	@Override
	public ShipmentType getOneShipmentType(Integer shipId) {
		return shipmentRepository.findById(shipId).orElseThrow(() -> {
			throw new ShipmentTypeNotFoundException("ShipmentType Object '" + shipId + "' Not Found");
		});
	}

	@Override
	public Integer updateOneShipmentType(ShipmentType shipmentType) {
		return shipmentRepository.save(shipmentType).getShipmentId();
	}

	@Override
	public boolean isShipmentTypeCodeCount(String shipmentCode) {

		return shipmentRepository.getShipmentTypeCodeCount(shipmentCode) > 0 ? true : false;
	}

	@Override
	public List<Object[]> generateShipmentModeAndCount() {

		return shipmentRepository.generateShipmentModeAndCount();
	}

	@Override
	public Map<Integer, String> getIdAndShipmentCodeByUsingEnableShipment(String enable) {
		List<Object[]> list = shipmentRepository.getIdAndShipmentCodeByUsingEnableShipment(enable);
		Map<Integer, String> map = ListToMapConfig.generateListToMap(list);
		return map;
	}

}
