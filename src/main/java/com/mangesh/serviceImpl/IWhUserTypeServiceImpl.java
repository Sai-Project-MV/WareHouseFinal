package com.mangesh.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.config.ListToMapConfig;
import com.mangesh.exception.WhUserTypeNotFoundException;
import com.mangesh.model.WhUserType;
import com.mangesh.repo.WhUserTypeRepository;
import com.mangesh.service.IWhUserTypeService;

@Service
public class IWhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepository whUserTypeRepository;

	@Override
	public Integer saveWhUserType(WhUserType whUserType) {

		return whUserTypeRepository.save(whUserType).getWhUserId();
	}

	@Override
	public List<WhUserType> getAllWhUserType() {

		return whUserTypeRepository.findAll();
	}

	@Override
	public void deleteWhUserTypeById(Integer whUserId) {
		whUserTypeRepository.deleteById(whUserId);

	}

	@Override
	public WhUserType getOneWhUserType(Integer whUserId) {

		return whUserTypeRepository.findById(whUserId).orElseThrow(() -> {
			throw new WhUserTypeNotFoundException("WhUser Not Found With Id" + whUserId);
		});
	}

	@Override
	public Integer updateOneWhUserType(WhUserType whUserType) {

		return whUserTypeRepository.save(whUserType).getWhUserId();
	}

	@Override
	public boolean isWhuserTypeEmailCount(String whUserEmail) {

		return whUserTypeRepository.getWhuserTypeEmailCount(whUserEmail) > 0 ? true : false;
	}

	@Override
	public boolean iswhUserContactCount(Long whUserContact) {

		return whUserTypeRepository.getwhUserContactCount(whUserContact) > 0 ? true : false;
	}

	@Override
	public List<Object[]> generatePieChartAndBarChart() {

		return whUserTypeRepository.generatePieChartAndBarChart();
	}

	@Override
	public Map<Integer, String> getWhUserIdAndWhUserCodebyUsingwhUserType1(String whUserType1) {
		List<Object[]> list = whUserTypeRepository.getWhUserIdAndWhUserCodebyUsingwhUserType1(whUserType1);
		Map<Integer, String> map = ListToMapConfig.generateListToMap(list);
		return map;
	}

}
