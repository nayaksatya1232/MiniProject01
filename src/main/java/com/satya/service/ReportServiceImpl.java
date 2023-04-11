package com.satya.service;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.satya.entity.CitizenPlan;
import com.satya.repo.CitizenPlanRepository;
import com.satya.request.SearchRequest;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private CitizenPlanRepository dao;

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
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row rowHeader = sheet.createRow(0);
		rowHeader.createCell(0).setCellValue("Id");
		rowHeader.createCell(1).setCellValue("PlanName");
		rowHeader.createCell(2).setCellValue("Status");
		rowHeader.createCell(3).setCellValue("Start Date");
		rowHeader.createCell(4).setCellValue("End Date");
		rowHeader.createCell(5).setCellValue("Benifit Amount");

		List<CitizenPlan> records = dao.findAll();
		int index = 1;
		for (CitizenPlan plan : records) {
			Row row = sheet.createRow(index);
			row.createCell(0).setCellValue(plan.getCitizenId());
			row.createCell(1).setCellValue(plan.getPlanName());
			row.createCell(2).setCellValue(plan.getPlanStatus());
			row.createCell(3).setCellValue(plan.getPlanStartDate());
			row.createCell(4).setCellValue(plan.getPlanEndDate());
			if (plan.getBenifitAmt() == null)
				row.createCell(5).setCellValue("n/a");
			else
				row.createCell(5).setCellValue(plan.getBenifitAmt());

			index++;
		}
		ServletOutputStream outStream = response.getOutputStream();
		workbook.write(outStream);
		outStream.close();
		workbook.close();

		return false;
	}

}
