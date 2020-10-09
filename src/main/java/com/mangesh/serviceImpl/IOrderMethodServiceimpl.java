package com.mangesh.serviceImpl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.config.ListToMapConfig;
import com.mangesh.controller.PartController;
import com.mangesh.exception.OrderMethodNotFoundException;
import com.mangesh.model.OrderMethod;
import com.mangesh.repo.OrderMethodRepository;
import com.mangesh.service.IOrderMethodService;

@Service
public class IOrderMethodServiceimpl implements IOrderMethodService {

	private static final Logger log = LoggerFactory.getLogger(PartController.class);

	@Autowired
	private OrderMethodRepository orderMethodRepository;

	@Override
	public Integer saveOrderMethod(OrderMethod orderMethod) {

		return orderMethodRepository.save(orderMethod).getOrdId();
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {

		return orderMethodRepository.findAll();
	}

	@Override
	public void deleteOrderMethodById(Integer ordId) {
		getOneOrderMethod(ordId);
		orderMethodRepository.deleteById(ordId);

	}

	@Override
	public OrderMethod getOneOrderMethod(Integer ordId) {

		return orderMethodRepository.findById(ordId).orElseThrow(() -> {
			throw new OrderMethodNotFoundException("Order Method Object Not Found with Id=>" + ordId);
		});
	}

	@Override
	public Integer updateOneOrderMethod(OrderMethod orderMethod) {

		return orderMethodRepository.save(orderMethod).getOrdId();
	}

	@Override
	public List<Object[]> generateOrderTypeChartAndCount() {

		return orderMethodRepository.generateOrderTypeChartAndCount();
	}

	@Override
	public Map<Integer, String> getgetOrderMethodOrdIdAndOrdCodeByUsingMode(String ordMode) {
		log.info("IOrderMethodServiceimpl.getgetOrderMethodOrdIdAndOrdCodeByUsingMode() execution started....!!");
		List<Object[]> list = orderMethodRepository.getOrderMethodOrdIdAndOrdCodeByUsingMode(ordMode);
		Map<Integer, String> map = ListToMapConfig.generateListToMap(list);
		log.info("IOrderMethodServiceimpl.getgetOrderMethodOrdIdAndOrdCodeByUsingMode() execution Ended....!!");
		return map;
	}

}
