package com.satya.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.satya.entity.CitizenPlan;
import com.satya.repo.CitizenPlanRepository;
import com.satya.request.SearchRequest;
import com.satya.util.EmailUtils;
import com.satya.util.ExcelGenerator;
import com.satya.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CitizenPlanRepository dao;
	@Autowired
	private ExcelGenerator excelGen;
	@Autowired
	private PdfGenerator pdfGen;
	@Autowired
	private EmailUtils emailUtil;

	@Override
	public List<String> getPlans() {
		return this.dao.getPlans();
	}

	@Override
	public List<String> getStatus() {
		return this.dao.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest r) {
		System.out.println(r);
		CitizenPlan citizen = new CitizenPlan();

		if (!"".equals(r.getPlanName()))
			citizen.setPlanName(r.getPlanName());
		if (!"".equals(r.getPlanStatus()))
			citizen.setPlanStatus(r.getPlanStatus());
		if (!"".equals(r.getGender()))
			citizen.setGender(r.getGender());
		if (null != r.getPlanStartDate())
			citizen.setPlanStartDate(r.getPlanStartDate());
		if (null != r.getPlanEndDate())
			citizen.setPlanEndDate(r.getPlanEndDate());

		return this.dao.findAll(Example.of(citizen));
	}

	@Override
	public void exportExcel(HttpServletResponse response) throws Exception {
		List<CitizenPlan> records = dao.findAll();
		File file = new File("Plan.xls");
		this.excelGen.generator(response, records,file);
		String subject = "Citizen-Plan-Details";
		String body = "<h1>Below the Excel File is attached</h1>";
		String to = "nayaksatya1232@gmail.com";
		this.emailUtil.sendMail(subject, body, to, file);
		file.delete();
	}

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		List<CitizenPlan> plans = dao.findAll();
		File file = new File("Plan.pdf");
		
		this.pdfGen.generatePdf(response, plans,file);
		String subject = "Citizen-Plan-Details";
		String body = "<h1>Below the Excel File is attached</h1>";
		String to = "nayaksatya1232@gmail.com";
		this.emailUtil.sendMail(subject, body, to, file);
		file.delete();
	}

}
