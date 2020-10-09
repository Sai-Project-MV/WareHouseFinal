package com.mangesh.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.mangesh.config.UomUtilConfig;
import com.mangesh.model.Uom;
import com.mangesh.report.UomExcelReport;
import com.mangesh.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService iUomService;
	
	@Autowired
	private UomUtilConfig config;

	@Autowired
	private ServletContext context;
	
	@GetMapping("/register")
	public String uomRegisterPage(Model model) {
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	@PostMapping("/save")
	public String uomStore(@ModelAttribute Uom uom, Model model) {
		Integer uomId = iUomService.storeUom(uom);
		String message = "UOM SAVED WITH ID:" + uomId;
		model.addAttribute("message", message);
		model.addAttribute("uom", new Uom());
		return "UomRegister";
	}

	@GetMapping("/all")
	public String uomAllData(Model model) {
		List<Uom> uomList = iUomService.getAllUom();
		model.addAttribute("uomList", uomList);
		return "UomData";
	}

	@GetMapping("/edit")
	public String editOneUom(@RequestParam Integer id, Model model) {
		Uom uom = iUomService.getOneUomById(id);
		model.addAttribute("uom", uom);
		return "EditUom";
	}
	
	@PostMapping("/update")
	public String updateOneRecord(Model model,@ModelAttribute Uom uom) {
		iUomService.updateUomById(uom);
		model.addAttribute("message","Uom Updated with Id"+uom.getUomId());
		return "redirect:all";
	}

	@GetMapping("/delete")
	public String deleteOneRecord(@RequestParam Integer id, Model model) {
		iUomService.deleteUomById(id);
		List<Uom> list = iUomService.getAllUom();
		String message = "One Record Deleted with Id: " + id;
		model.addAttribute("message", message);
		model.addAttribute("uomList", list);
		return "UomData";
	}

	@GetMapping("/excel")
	public ModelAndView generateExcelReport() {
		ModelAndView m = new ModelAndView();
		m.setView((View) new UomExcelReport());
		m.addObject("list", iUomService.getAllUom());
		return m;
	}
	
	@GetMapping("/validate")
	public @ResponseBody String checkUomModelDuplicateOrNot(@RequestParam String model) {
		String message="";
		if(iUomService.isUomModelCount(model))
			message="Uom Model "+model+" Is Allready Exits";
		return message;
	}
	
	@GetMapping("/chart")
	public String getPieChart() {
		List<Object[]> list = iUomService.getUomTypeAndCount();
		 String path = context.getRealPath("/");
		config.generatePieChart(path, list);
		config.generateBarChart(path, list);
		return "uomPieChart";
	}
}
