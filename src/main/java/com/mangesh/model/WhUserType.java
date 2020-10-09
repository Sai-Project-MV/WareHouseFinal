package com.mangesh.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "whuser_type_tab")
public class WhUserType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer whUserId;
	private String whUserType1;
	private String whuserfor;
	private String whUserCode;
	private String whUserEmail;
	private Long whUserContact;
	private String whUserIdType;
	private String ifOther;
	private String whUserIdNumber;
	
}
