package com.mangesh.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.model.PurchaseOrder;
import com.mangesh.repo.PurchaseOrderRepository;
import com.mangesh.service.PurchaseOrderService;

@Service
public class PurchaseOrderImpl implements PurchaseOrderService {

	private static final Logger log = LoggerFactory.getLogger(PurchaseOrderImpl.class);
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public Integer savepurchaseOrder(PurchaseOrder purchaseOrder) {
		log.info("PurchaseOrderImpl.savepurchaseOrder() Execution started...!!");
		Integer prchId = purchaseOrderRepository.save(purchaseOrder).getPrchId();
		log.info("PurchaseOrderImpl.savepurchaseOrder() Execution Ended...!!");
		return prchId;
	}

}
