package com.mangesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "sale_order_tab")
public class SaleOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sale_id_col")
	private Integer saleId;
	@Column(name = "sale_order_code_col")
	private String saleOrderCode;
	@Column(name = "sale_ref_num_col")
	private Integer saleRefNumber;
	@Column(name = "sale_quality_chk_col")
	private String saleQualityCheck;
	@Column(name = "sale_stock_col")
	private String saleStockSource;
	@Column(name = "sale_status_col")
	private String status;
	@Column(name = "sale_desc_col")
	private String saleDesc;

	// Association mapping

	@ManyToOne
	@JoinColumn(name = "shipment_type_id_fk_col")
	private ShipmentType shipmentType;

	@ManyToOne
	@JoinColumn(name = "whuser__type_id_fk_col")
	private WhUserType customer;

}
