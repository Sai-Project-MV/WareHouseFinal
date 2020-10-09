package com.mangesh.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mangesh.exception.PartNotFoundException;
import com.mangesh.model.Part;
import com.mangesh.repo.PartRepository;
import com.mangesh.service.PartService;

@Service
public class PartServiceImpl implements PartService {

	private static final Logger log = LoggerFactory.getLogger(PartServiceImpl.class);

	@Autowired
	private PartRepository partRepository;

	@Override
	public Integer savePart(Part part) {
		log.info("PartServiceImpl.savePart() method execution started...!!");
		Integer id = partRepository.save(part).getId();
		log.info("PartServiceImpl.savePart() method execution Ended...!!");
		return id;
	}

	@Override
	public List<Part> getAllPart() {
		log.info("PartServiceImpl.getAllPart() method execution started....!!");
		List<Part> list = partRepository.findAll();
		log.info("PartServiceImpl.getAllPart() method execution Ended....!!");
		return list;
	}

	@Override
	public void deleteOnePart(Integer partId) {
		log.info("PartServiceImpl.deleteOnePart() Execution started....!!");
		partRepository.deleteById(partId);
		log.info("PartServiceImpl.deleteOnePart() Execution Ended....!!");
	}

	@Override
	public Part editOneRecord(Integer partId) {
		log.info("PartServiceImpl.editOneRecord() Execution started...!!");
		Part part = partRepository.findById(partId).orElseThrow(() -> {
			throw new PartNotFoundException("Part Not Found With Id" + partId);
		});
		log.info("PartServiceImpl.editOneRecord() Execution Ended...!!");
		return part;
	}
}
