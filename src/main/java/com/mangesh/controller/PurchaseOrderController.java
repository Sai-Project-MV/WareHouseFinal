package com.mangesh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mangesh.model.PurchaseOrder;
import com.mangesh.service.IShipmentTypeService;
import com.mangesh.service.IWhUserTypeService;
import com.mangesh.service.PurchaseOrderService;

@Controller
@RequestMapping("/prch")
public class PurchaseOrderController {

	private static final Logger log = LoggerFactory.getLogger(PurchaseOrderController.class);

	@Autowired
	private PurchaseOrderService prchService;

	@Autowired
	private IShipmentTypeService shipService;
	
	@Autowired
	private IWhUserTypeService whService;

	private void dyanamicUomModel(Model model) {
		model.addAttribute("shipmentCode", shipService.getIdAndShipmentCodeByUsingEnableShipment("Yes"));
	    model.addAttribute("whUserCode", whService.getWhUserIdAndWhUserCodebyUsingwhUserType1("Vendor"));
	}

	@GetMapping("/register")
	public String registerprchOrderPage(Model model) {
		log.info("PurchaseOrderController.registerprchOrderPage() Execution Started...!!!");
		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder.setStatus("OPEN");
		model.addAttribute("purchaseOrder", purchaseOrder);
		dyanamicUomModel(model);
		log.info("PurchaseOrderController.registerprchOrderPage() Execution Ended...!!!");
		return "purchaseOrderRegister";
	}

	@PostMapping("/save")
	public String savePrchOrder(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {
		log.info("PurchaseOrderController.savePrchOrder() Execution Started...!!!");
		Integer orderId = prchService.savepurchaseOrder(purchaseOrder);
		model.addAttribute("message", "Purchase Order Saved With Id=>" + orderId);
		PurchaseOrder purchase = new PurchaseOrder();
		purchase.setStatus("OPEN");
		model.addAttribute("purchaseOrder", purchaseOrder);
		log.info("PurchaseOrderController.savePrchOrder() Execution Ended...!!!");
		return "purchaseOrderRegister";
	}
}
