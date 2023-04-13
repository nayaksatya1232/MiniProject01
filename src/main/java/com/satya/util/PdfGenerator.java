package com.satya.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.satya.entity.CitizenPlan;

@Component
public class PdfGenerator {
	public void generatePdf(HttpServletResponse response, List<CitizenPlan> plans, File file) throws Exception {
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, response.getOutputStream());
		PdfWriter.getInstance(doc, new FileOutputStream(file));
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
