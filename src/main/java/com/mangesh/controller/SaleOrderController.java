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

import com.mangesh.model.SaleOrder;
import com.mangesh.service.IShipmentTypeService;
import com.mangesh.service.IWhUserTypeService;
import com.mangesh.service.SaleOrderService;

@Controller
@RequestMapping("/sale")
public class SaleOrderController {

	private static final Logger log = LoggerFactory.getLogger(SaleOrderController.class);

	@Autowired
	private IShipmentTypeService shipService;

	@Autowired
	private IWhUserTypeService whService;

	@Autowired
	private SaleOrderService saleService;

	private void dyanamicUomModel(Model model) {
		log.info("SaleOrderController.dyanamicUomModel() Execution Started...!!");
		model.addAttribute("shipmentCode", shipService.getIdAndShipmentCodeByUsingEnableShipment("Yes"));
		model.addAttribute("whUserCode", whService.getWhUserIdAndWhUserCodebyUsingwhUserType1("Customer"));
		log.info("SaleOrderController.dyanamicUomModel() Execution Ended...!!");
	}

	@GetMapping("/register")
	public String saleRegisterPage(Model model) {
		log.info("SaleOrderController.saleRegisterPage() Execution Started...!!");
		SaleOrder saleOrder = new SaleOrder();
		saleOrder.setStatus("SALE-OPEN");
		dyanamicUomModel(model);
		model.addAttribute("saleOrder", saleOrder);
		log.info("SaleOrderController.saleRegisterPage() Execution Ended...!!");
		return "saleOrderRegister";
	}

	@PostMapping("/save")
	public String saveSaleOrder(@ModelAttribute SaleOrder saleOrder, Model model) {
		log.info("SaleOrderController.saveSaleOrder() Execution Started...!!!");
		Integer saleOrderId = saleService.saveSaleOrder(saleOrder);
		model.addAttribute("message", "Sale Order Saved With Id=>" + saleOrderId);
		SaleOrder sale = new SaleOrder();
		sale.setStatus("SALE-OPEN");
		dyanamicUomModel(model);
		model.addAttribute("saleOrder", saleOrder);
		log.info("SaleOrderController.saveSaleOrder() Execution Ended...!!!");
		return "saleOrderRegister";
	}

	@GetMapping("/all")
	public String getAllSaleOrder(Model model) {
		log.info("SaleOrderController.getAllSaleOrder() Execution Started...!!!");
		List<SaleOrder> list = saleService.getAllSaleOrders();
		model.addAttribute("saleOrderList", list);
		log.info("SaleOrderController.getAllSaleOrder() Execution Ended...!!!");
		return "saleOrderData";
	}

	@GetMapping("/edit")
	public String getOneRecordForEdit(@RequestParam Integer id, Model model) {
		log.info("SaleOrderController.getOneRecordForEdit()  Execution Started...!!!" + id);
		SaleOrder saleOrder = saleService.getOneSaleOrder(id);
		model.addAttribute("saleOrder", saleOrder);
		dyanamicUomModel(model);
		log.info("SaleOrderController.getOneRecordForEdit()  Execution Ended...!!!" + saleOrder.toString());
		return "saleOrderEdit";
	}
}
