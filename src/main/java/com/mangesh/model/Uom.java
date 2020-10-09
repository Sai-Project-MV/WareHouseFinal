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
@Table(name = "uom_tab")
public class Uom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uom_id_col")
	private Integer uomId;
	@Column(name = "uom_type_col")
	private String uomType;
	@Column(name = "uom_model_col")
	private String uomModel;
	@Column(name = "uom_description_col")
	private String description;

}
