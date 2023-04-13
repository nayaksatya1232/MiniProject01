package com.satya.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import com.satya.entity.CitizenPlan;

@Component
public class ExcelGenerator {
	public void generator(HttpServletResponse response, List<CitizenPlan> records, File f) throws Exception {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("plans-data");
		Row rowHeader = sheet.createRow(0);
		rowHeader.createCell(0).setCellValue("Id");
		rowHeader.createCell(1).setCellValue("PlanName");
		rowHeader.createCell(2).setCellValue("Status");
		rowHeader.createCell(3).setCellValue("Start Date");
		rowHeader.createCell(4).setCellValue("End Date");
		rowHeader.createCell(5).setCellValue("Benifit Amount");

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
		FileOutputStream fos = new FileOutputStream(f);
		workbook.write(fos);
		fos.close();
		workbook.close();

		ServletOutputStream outStream = response.getOutputStream();
		workbook.write(outStream);
		outStream.close();
		workbook.close();
	}
}
