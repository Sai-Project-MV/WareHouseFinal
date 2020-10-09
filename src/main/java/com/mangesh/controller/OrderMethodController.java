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

import com.mangesh.config.OrderUtilConfig;
import com.mangesh.model.OrderMethod;
import com.mangesh.service.IOrderMethodService;

@Controller
@RequestMapping("/order")
public class OrderMethodController {

	@Autowired
	private IOrderMethodService iOrderMethodService;

	@Autowired
	private OrderUtilConfig config;

	@Autowired
	private ServletContext context;

	@GetMapping("/register")
	public String orderMethodPage(Model model) {
		model.addAttribute("orderMethod", new OrderMethod());
		return "orderMethodRegister";
	}

	@PostMapping("/save")
	public String orderMethodSave(@ModelAttribute OrderMethod orderMethod, Model model) {
		Integer orderMethodId = iOrderMethodService.saveOrderMethod(orderMethod);
		String message = "ORDER METHOD SAVED WITH ID:" + orderMethodId;
		model.addAttribute("message", message);
		model.addAttribute("orderMethod", new OrderMethod());
		return "orderMethodRegister";
	}

	@GetMapping("/all")
	public String orderMethodAllData(Model model) {
		List<OrderMethod> orderMethodList = iOrderMethodService.getAllOrderMethod();
		model.addAttribute("orderMethodList", orderMethodList);
		return "orderMethodData";
	}

	@GetMapping("/delete")
	public String deleteOneRecord(@RequestParam Integer id, Model model) {
		iOrderMethodService.deleteOrderMethodById(id);
		List<OrderMethod> list = iOrderMethodService.getAllOrderMethod();
		String message = "One Record Deleted with Id: " + id;
		model.addAttribute("message", message);
		model.addAttribute("orderMethodList", list);
		return "orderMethodData";
	}

	@GetMapping("/edit")
	public String editOneOrderMethod(@RequestParam Integer id, Model model) {
		OrderMethod orderMethod = iOrderMethodService.getOneOrderMethod(id);
		model.addAttribute("orderMethod", orderMethod);
		return "orderMethodEdit";
	}

	@PostMapping("/update")
	public String updateOneRecord(@ModelAttribute OrderMethod orderMethod) {
		iOrderMethodService.updateOneOrderMethod(orderMethod);
		return "redirect:all";
	}

	@GetMapping("/chart")
	public String generatePieAndBarChart() {
		String path = context.getRealPath("/");
		List<Object[]> list = iOrderMethodService.generateOrderTypeChartAndCount();
		config.generatePieChart(path, list);
		config.generateBarChart(path, list);
		return "orderChart";
	}

}
