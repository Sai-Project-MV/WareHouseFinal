package com.mangesh.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "order_method_tab")
public class OrderMethod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ordId;
	private String ordMode;
	private String ordCode;
	private String ordType;
	@ElementCollection
	@CollectionTable(name = "ORDER_ACCEPT_TAB",joinColumns = @JoinColumn(name="ordId"))
	private List<String> ordAccept;
	private String ordDescription;
}
