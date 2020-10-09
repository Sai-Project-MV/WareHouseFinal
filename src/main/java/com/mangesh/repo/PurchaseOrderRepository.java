package com.mangesh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
