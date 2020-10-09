package com.mangesh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "shipment_type_tab")
public class ShipmentType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipment_id_col") 
	private Integer shipmentId;
	@Column(name = "shipment_mode_col")
	private String shipmentMode;
	@Column(name = "shipment_code_col",unique = true)
	private String shipmentCode;
	@Column(name = "shipment_enable_col")
	private String enableShipment;
	@Column(name = "shipment_grade_col")
	private String shipmentGrade;
	@Column(name = "shipment_description_col")
	private String description;

}
