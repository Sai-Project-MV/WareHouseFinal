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

import com.mangesh.config.ShipmentUtilConfig;
import com.mangesh.model.ShipmentType;
import com.mangesh.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

	@Autowired
	private IShipmentTypeService iShipmentTypeService;

	@Autowired
	private ShipmentUtilConfig config;

	@Autowired
	private ServletContext context;

	@GetMapping("/register")
	public String shipmentRegisterPage(Model model) {
		model.addAttribute("shipment", new ShipmentType());
		return "ShipmentRegister";
	}

	@PostMapping("/save")
	public String shipmentTypeStore(@ModelAttribute ShipmentType shipmentType, Model model) {
		Integer shipmentTypeId = iShipmentTypeService.saveShipmentType(shipmentType);
		String message = "SHIPMENTTYPE SAVED WITH ID:" + shipmentTypeId;
		model.addAttribute("message", message);
		model.addAttribute("shipment", new ShipmentType());
		return "ShipmentRegister";
	}

	@GetMapping("/all")
	public String uomAllData(Model model) {
		List<ShipmentType> shipmentList = iShipmentTypeService.getAllShipmentType();
		model.addAttribute("shipmentList", shipmentList);
		return "ShipmentTypeData";
	}

	@GetMapping("/delete")
	public String deleteOneRecord(@RequestParam Integer id, Model model) {
		iShipmentTypeService.deleteShipmentById(id);
		List<ShipmentType> list = iShipmentTypeService.getAllShipmentType();
		String message = "One Record Deleted with Id: " + id;
		model.addAttribute("message", message);
		model.addAttribute("shipmentList", list);
		return "ShipmentTypeData";
	}

	@GetMapping("/edit")
	public String editOneShipmentType(@RequestParam Integer id, Model model) {
		ShipmentType shipmentType = iShipmentTypeService.getOneShipmentType(id);
		model.addAttribute("shipmentType", shipmentType);
		return "EditShipmentType";
	}

	@PostMapping("/update")
	public String updateOneRecord(@ModelAttribute ShipmentType shipmentType) {
		iShipmentTypeService.updateOneShipmentType(shipmentType);
		return "redirect:all";
	}

	@GetMapping("/validate")
	public @ResponseBody String validateShipmentTypeCode(@RequestParam String code) {
		String message = "";
		if (iShipmentTypeService.isShipmentTypeCodeCount(code))
			message = "Shipment Type " + code + " Allready Availeble enter other Code";
		return message;
	}

	@GetMapping("/chart")
	public String generatePieAndBarChart() {
		String path = context.getRealPath("/");
		List<Object[]> list = iShipmentTypeService.generateShipmentModeAndCount();
		config.generatePieChart(path, list);
		config.generateBarChart(path, list);
		return "shipmentChart";
	}
}
