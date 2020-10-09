package com.mangesh.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.exception.SaleOrderNotFoundException;
import com.mangesh.model.SaleOrder;
import com.mangesh.repo.SaleOrderRepository;
import com.mangesh.service.SaleOrderService;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {

	private static final Logger log = LoggerFactory.getLogger(SaleOrderServiceImpl.class);

	@Autowired
	private SaleOrderRepository saleOrderRepository;

	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		log.info("SaleOrderServiceImpl.saveSaleOrder() Execution Started...!!");
		Integer saleId = saleOrderRepository.save(saleOrder).getSaleId();
		log.info("SaleOrderServiceImpl.saveSaleOrder() Execution Ended...!!");
		return saleId;
	}

	@Override
	public List<SaleOrder> getAllSaleOrders() {
		log.info("SaleOrderServiceImpl.getAllSaleOrders() Execution Started...!!");
		List<SaleOrder> list = saleOrderRepository.findAll();
		log.info("SaleOrderServiceImpl.getAllSaleOrders() Execution Ended...!!");
		return list;
	}

	@Override
	public SaleOrder getOneSaleOrder(Integer saleId) {
		log.info("SaleOrderServiceImpl.getOneSaleOrder() Execution Started...!!");
		return saleOrderRepository.findById(saleId).orElseThrow(() -> {
			throw new SaleOrderNotFoundException("SaleOrder Not Found With Id" + saleId);
		});

	}

	@Override
	public void deleteOneSaleOrder(Integer saleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateOneSaleOrder(SaleOrder saleOrder) {
		// TODO Auto-generated method stub

	}

}
