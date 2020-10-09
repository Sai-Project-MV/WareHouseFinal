package com.mangesh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mangesh.model.Part;
import com.mangesh.service.IOrderMethodService;
import com.mangesh.service.IUomService;
import com.mangesh.service.PartService;

/**
 * 
 * @author mvadk Part Controller
 *
 */
@Controller
@RequestMapping("/part")
public class PartController {

	private static final Logger log = LoggerFactory.getLogger(PartController.class);

	@Autowired
	private IUomService service;

	@Autowired
	private IOrderMethodService ordService;

	@Autowired
	private PartService partService;

	private void dyanamicUomModel(Model model) {
		log.info("dynamicUomModel method execution started...!!");
		model.addAttribute("uoms", service.getUomIdAndModel());
		model.addAttribute("sales", ordService.getgetOrderMethodOrdIdAndOrdCodeByUsingMode("Sale"));
		model.addAttribute("purchase", ordService.getgetOrderMethodOrdIdAndOrdCodeByUsingMode("Purchase"));
		log.info("dynamicUomModel method execution Ended...!!");
	}

	@GetMapping("/register")
	public String registerPage(Model model) {
		log.info("PartController.registerPage() method execution started...!!");
		model.addAttribute("part", new Part());
		dyanamicUomModel(model);
		log.info("PartController.registerPage() method execution Ended...!!");
		return "partRegister";
	}

	@PostMapping("/save")
	public String savePart(@ModelAttribute Part part, Model model) {
		log.info("PartController.savePart() method execution started...!!" + part.toString());
		Integer partId = partService.savePart(part);
		model.addAttribute("message", "Part Saved with" + partId);
		model.addAttribute("part", new Part());
		log.info("PartController.savePart() method execution Ended...!!");
		return "partRegister";
	}

	@GetMapping("/all")
	public String getAllPart(Model model) {
		log.info("PartController.getAllPart() method execution started...!!");
		List<Part> list = partService.getAllPart();
		model.addAttribute("partlist", list);
		log.info("PartController.getAllPart() method execution Ended...!!" + list.toString());
		return "partData";
	}

	@GetMapping("/delete")
	public String deleteOnePart(@RequestParam Integer id, Model model) {
		log.info("PartController.deleteOnePart() Execution started....!!" + id);
		partService.deleteOnePart(id);
		model.addAttribute("message", "One Record Deleted With Id " + id);
		log.info("PartController.deleteOnePart() Execution Ended....!!");
		return "partData";
	}

	@GetMapping("/edit")
	public String editOneRecord(@RequestParam Integer id, Model model) {
		log.info("PartController.editOneRecord() Execution started...!!" + id);
		Part part = partService.editOneRecord(id);
		dyanamicUomModel(model);
		model.addAttribute("part", part);
		log.info("PartController.editOneRecord() Execution Ended...!!" + part.toString());
		return "partEdit";
	}
}
