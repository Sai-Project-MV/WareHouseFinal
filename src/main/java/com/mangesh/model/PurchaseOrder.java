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
@Table(name = "purchase_order_tab")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prch_id_col")
	private Integer prchId;
	@Column(name = "prch_order_code_col")
	private String prchOrderCode;
	@Column(name = "prch_ref_num_col")
	private Integer prchRefNumber;
	@Column(name = "prch_quality_chk_col")
	private String prchQualityCheck;
	@Column(name = "prch_status_col")
	private String status;
	@Column(name = "prch_desc_col")
	private String prchDesc;

	// Association mapping

	@ManyToOne
	@JoinColumn(name = "shipment_type_id_fk_col")
	private ShipmentType shipmentType;

	@ManyToOne
	@JoinColumn(name = "whuser__type_id_fk_col")
	private WhUserType vendor;
}
