package com.mangesh.service;

import java.util.List;

import com.mangesh.model.Documents;

public interface DocumentService {

	void saveDocument(Documents document);
	List<Object[]> getDocumentIdAndName();
}
