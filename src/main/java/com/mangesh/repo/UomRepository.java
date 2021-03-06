package com.mangesh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mangesh.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer> {

	@Query("SELECT COUNT(uomModel) FROM Uom WHERE uomModel=:uomModel")
	Integer getUomModelCount(String uomModel);

	@Query("SELECT uomType,COUNT(uomType) FROM Uom GROUP BY uomType")
	List<Object[]> getUomTypeAndCount();

	@Query("SELECT uomId,uomModel FROM Uom")
	List<Object[]> getUomIdAndModel();

}
