package com.mangesh.service;

import java.util.List;
import java.util.Map;

import com.mangesh.model.WhUserType;


public interface IWhUserTypeService {
	
	public Integer saveWhUserType(WhUserType whUserType);

	public List<WhUserType> getAllWhUserType();

	public void deleteWhUserTypeById(Integer whUserId);

	public WhUserType getOneWhUserType(Integer whUserId);

	public Integer updateOneWhUserType(WhUserType whUserType);
	
	boolean isWhuserTypeEmailCount(String whUserEmail);
	
	boolean iswhUserContactCount(Long whUserContact);
	
	List<Object[]> generatePieChartAndBarChart();

	Map<Integer,String> getWhUserIdAndWhUserCodebyUsingwhUserType1(String whUserType1);
}
