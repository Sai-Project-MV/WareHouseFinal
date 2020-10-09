package com.mangesh.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.model.Documents;
import com.mangesh.repo.DocumentRepository;
import com.mangesh.service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository documentRepository;

	@Override
	public void saveDocument(Documents document) {
		documentRepository.save(document);
	}

	@Override
	public List<Object[]> getDocumentIdAndName() {
		
		return documentRepository.getDocumentIdAndName();
	}
}
