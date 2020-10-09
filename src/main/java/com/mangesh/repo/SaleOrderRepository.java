package com.mangesh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.model.SaleOrder;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Integer>{

}
