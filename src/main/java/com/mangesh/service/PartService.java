package com.mangesh.service;

import java.util.List;

import com.mangesh.model.Part;

public interface PartService {

	public Integer savePart(Part part);

	public List<Part> getAllPart();

	public void deleteOnePart(Integer partId);

	public Part editOneRecord(Integer partId);
}
