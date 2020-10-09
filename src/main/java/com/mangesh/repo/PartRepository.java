package com.mangesh.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mangesh.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer> {

}
