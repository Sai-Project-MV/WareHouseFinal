package com.mangesh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mangesh.model.Documents;

public interface DocumentRepository extends JpaRepository<Documents, Integer> {
	
	@Query("SELECT docId,docName FROM Documents")
	List<Object[]> getDocumentIdAndName();

}
