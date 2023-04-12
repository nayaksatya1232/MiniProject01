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

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
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

			if (plan.getPlanStartDate() == null)
				row.createCell(3).setCellValue("n/a");
			else
				row.createCell(3).setCellValue(plan.getPlanStartDate() + "");

			if (plan.getPlanEndDate() == null)
				row.createCell(4).setCellValue("n/a");
			else
				row.createCell(4).setCellValue(plan.getPlanEndDate() + "");

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

	@Override
	public void exportPdf(HttpServletResponse response) throws Exception {
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph = new Paragraph("--Plan Detatils--", fontTiltle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		doc.add(paragraph);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 6, 6, 6, 6, 6, 6 });
		table.setSpacingBefore(5);

		table.addCell("Id");
		table.addCell("PlanName");
		table.addCell("PlanStatus");
		table.addCell("StartDate");
		table.addCell("EndDate");
		table.addCell("Amount");

		List<CitizenPlan> plans = dao.findAll();

		for (CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			table.addCell(plan.getPlanStartDate() + "");
			table.addCell(plan.getPlanEndDate() + "");
			table.addCell(String.valueOf(plan.getBenifitAmt()));
		}

		doc.add(table);
		doc.close();
	}

}
