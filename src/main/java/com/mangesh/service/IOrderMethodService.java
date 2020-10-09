package com.mangesh.service;

import java.util.List;
import java.util.Map;

import com.mangesh.model.OrderMethod;

public interface IOrderMethodService {

	public Integer saveOrderMethod(OrderMethod orderMethod);

	public List<OrderMethod> getAllOrderMethod();

	public void deleteOrderMethodById(Integer ordId);

	public OrderMethod getOneOrderMethod(Integer ordId);

	public Integer updateOneOrderMethod(OrderMethod orderMethod);

	List<Object[]> generateOrderTypeChartAndCount();

	Map<Integer, String> getgetOrderMethodOrdIdAndOrdCodeByUsingMode(String ordMode);

}
