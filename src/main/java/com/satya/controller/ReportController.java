package com.satya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.satya.entity.CitizenPlan;
import com.satya.request.SearchRequest;
import com.satya.service.ReportService;

@Controller
public class ReportController {
	@Autowired
	private ReportService reportService;

	@GetMapping("/")
	public String homePage(Model model) {
		model.addAttribute("search",new SearchRequest());
		this.init(model);
		return "index";
	}
	@PostMapping("/search")
	public String searchHandler(@ModelAttribute("search")SearchRequest request,Model model) {
		List<CitizenPlan> list = this.reportService.search(request);
		model.addAttribute("records",list);
		init(model);
		return "index";
	}
	public void init(Model model) {
		model.addAttribute("plans",this.reportService.getPlans());
		model.addAttribute("statuses",this.reportService.getStatus());
	}
}
