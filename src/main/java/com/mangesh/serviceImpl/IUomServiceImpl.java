package com.mangesh.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.config.ListToMapConfig;
import com.mangesh.exception.UomNotFoundException;
import com.mangesh.model.Uom;
import com.mangesh.repo.UomRepository;
import com.mangesh.service.IUomService;

@Service
public class IUomServiceImpl implements IUomService {

	@Autowired
	private UomRepository uomRepository;

	@Override
	public Integer storeUom(Uom uom) {
		return uomRepository.save(uom).getUomId();
	}

	@Override
	public List<Uom> getAllUom() {
		List<Uom> list = uomRepository.findAll();
		list.sort((uom1, uom2) -> uom1.getUomId() - uom2.getUomId());
		return list;
	}

	@Override
	public void deleteUomById(Integer uomId) {
		Optional<Uom> uom = uomRepository.findById(uomId);
		if (uom.isPresent()) {
			uom.get().getUomId();
			uomRepository.deleteById(uom.get().getUomId());
		} else {

		}

	}

	@Override
	public Uom getOneUomById(Integer id) {

		return uomRepository.findById(id).orElseThrow(() -> {
			throw new UomNotFoundException("UOM Not Found Please Enter Another Id");
		});
	}

	@Override
	public void updateUomById(Uom uom) {
		uomRepository.save(uom);
	}

	@Override
	public boolean isUomModelCount(String uomModel) {

		return uomRepository.getUomModelCount(uomModel) > 0 ? true : false;
	}

	@Override
	public List<Object[]> getUomTypeAndCount() {

		return uomRepository.getUomTypeAndCount();
	}

	@Override
	public Map<Integer, String> getUomIdAndModel() {
		List<Object[]> list = uomRepository.getUomIdAndModel();
		Map<Integer, String> map = ListToMapConfig.generateListToMap(list);
		return map;
	}

}
