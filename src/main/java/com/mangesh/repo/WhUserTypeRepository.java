package com.mangesh.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mangesh.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	
	@Query("SELECT COUNT(whUserEmail) FROM WhUserType WHERE whUserEmail=:whUserEmail")
	Integer getWhuserTypeEmailCount(String whUserEmail);
	
	@Query("SELECT COUNT(whUserContact) FROM WhUserType WHERE whUserContact=:whUserContact")
	Integer getwhUserContactCount(Long whUserContact);

	@Query("SELECT whUserIdType,COUNT(whUserIdType) FROM WhUserType GROUP BY whUserIdType")
	List<Object[]> generatePieChartAndBarChart();
	
	@Query("SELECT whUserId,whUserCode FROM WhUserType WHERE whUserType1=:whUserType1")
	List<Object[]> getWhUserIdAndWhUserCodebyUsingwhUserType1(String whUserType1);
}
