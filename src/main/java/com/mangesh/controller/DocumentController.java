package com.mangesh.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mangesh.exception.SelectFileAndIdException;
import com.mangesh.model.Documents;
import com.mangesh.service.DocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;

	@GetMapping("/all")
	public String getDocumentPage(Model model) {
		List<Object[]> list = documentService.getDocumentIdAndName();
		model.addAttribute("list",list);
		return "documentPage";
	}

	@PostMapping("/save")
	public String saveDocs(@RequestParam Integer fId, @RequestParam MultipartFile fob) throws SelectFileAndIdException {
		if (fob != null) {
			try {
				Documents doc = new Documents();
				doc.setDocId(fId);
				doc.setDocName(fob.getOriginalFilename());
				doc.setDocData(fob.getBytes());
				documentService.saveDocument(doc);
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			
			throw new SelectFileAndIdException("Please Select Id and Document");
		}
		return "redirect:all";
	}
}
