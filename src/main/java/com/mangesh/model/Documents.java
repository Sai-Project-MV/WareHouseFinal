package com.mangesh.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="document_tab")
public class Documents {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docId;
	private String docName;
	@Lob
	private byte[] docData;

}
