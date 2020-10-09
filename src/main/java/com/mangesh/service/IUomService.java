package com.mangesh.service;

import java.util.List;
import java.util.Map;

import com.mangesh.model.Uom;

/**
 * 
 * @author mvadk
 *
 */
public interface IUomService {

	public Integer storeUom(Uom uom);

	public List<Uom> getAllUom();

	public void deleteUomById(Integer uomId);

	public Uom getOneUomById(Integer id);

	public void updateUomById(Uom uom);

	boolean isUomModelCount(String uomModel);

	List<Object[]> getUomTypeAndCount();

	Map<Integer, String> getUomIdAndModel();
}
