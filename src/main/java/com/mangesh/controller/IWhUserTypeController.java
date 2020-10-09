package com.mangesh.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mangesh.config.EmailUtil;
import com.mangesh.config.WhUserUtilConfig;
import com.mangesh.model.WhUserType;
import com.mangesh.service.IWhUserTypeService;

@Controller
@RequestMapping("/whuser")
public class IWhUserTypeController {

	@Autowired
	private IWhUserTypeService iWhUserTypeService;

	@Autowired
	private WhUserUtilConfig config;

	@Autowired
	private EmailUtil email;

	@Autowired
	private ServletContext context;

	@Value("${user.mail}")
	private String successMessage;

	@GetMapping("/register")
	public String whUserTypePage(Model model) {
		model.addAttribute("whUserType", new WhUserType());
		return "whUserTypeRegister";
	}

	@PostMapping("/save")
	public String whUserTypeSave(@ModelAttribute WhUserType whUserType, Model model) {
		Integer whUserTypeId = iWhUserTypeService.saveWhUserType(whUserType);
		String message = "WHUSER TYPE SAVED WITH ID:" + whUserTypeId;

		new Thread(() -> {
			if (whUserTypeId != null && whUserTypeId > 0) {
				email.sendMail(whUserType.getWhUserEmail(), "Welcome To WHUserType", "Hello "
						+ whUserType.getWhUserCode() + ",\n"+successMessage + whUserTypeId);
			}
		}).start();
		model.addAttribute("message", message);
		model.addAttribute("orderMethod", new WhUserType());
		return "whUserTypeRegister";
	}

	@GetMapping("/all")
	public String whUserTypeAllData(Model model) {
		List<WhUserType> whUserTypeList = iWhUserTypeService.getAllWhUserType();
		model.addAttribute("whUserTypeList", whUserTypeList);
		return "whUserTypeData";
	}

	@GetMapping("/delete")
	public String deleteOneRecord(@RequestParam Integer id, Model model) {
		iWhUserTypeService.deleteWhUserTypeById(id);
		List<WhUserType> list = iWhUserTypeService.getAllWhUserType();
		String message = "One Record Deleted with Id: " + id;
		model.addAttribute("message", message);
		model.addAttribute("whUserTypeList", list);
		return "whUserTypeData";
	}

	@GetMapping("/edit")
	public String editOneWhUserType(@RequestParam Integer id, Model model) {
		WhUserType whUserType = iWhUserTypeService.getOneWhUserType(id);
		model.addAttribute("whUserType", whUserType);
		return "whUserTypeEdit";
	}

	@PostMapping("/update")
	public String updateOneRecord(@ModelAttribute WhUserType whUserType) {
		iWhUserTypeService.updateOneWhUserType(whUserType);
		return "redirect:all";
	}

	@GetMapping("/validateEmail")
	public @ResponseBody String isEmailDuplicateOrNot(@RequestParam String email) {
		String message = "";
		if (iWhUserTypeService.isWhuserTypeEmailCount(email))
			message = "Allready Availeble " + email + " Not possible ";
		return message;
	}

	@GetMapping("/validateContact")
	public @ResponseBody String isContactDuplicateOrNot(@RequestParam Long contact) {
		String message = "";
		if (iWhUserTypeService.iswhUserContactCount(contact))
			message = "Allready Availeble " + contact + " Not possible ";
		return message;
	}

	@GetMapping("/chart")
	public String getPieChart() {
		List<Object[]> list = iWhUserTypeService.generatePieChartAndBarChart();
		String path = context.getRealPath("/");
		config.generatePieChart(path, list);
		config.generateBarChart(path, list);
		return "whUserChart";
	}
}
