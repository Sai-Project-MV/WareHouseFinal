package com.mangesh.service;

import java.util.List;

import com.mangesh.model.SaleOrder;

public interface SaleOrderService {

	public Integer saveSaleOrder(SaleOrder saleOrder);

	public List<SaleOrder> getAllSaleOrders();

	public SaleOrder getOneSaleOrder(Integer saleId);

	public void deleteOneSaleOrder(Integer saleId);

	public void updateOneSaleOrder(SaleOrder saleOrder);
}
