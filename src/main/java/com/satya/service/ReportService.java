package com.satya.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.satya.entity.CitizenPlan;
import com.satya.request.SearchRequest;

public interface ReportService {
	public List<String> getPlans();
	public List<String> getStatus();
	public List<CitizenPlan> search(SearchRequest request);
	public void exportExcel(HttpServletResponse response) throws Exception ;
	public void exportPdf(HttpServletResponse response) throws Exception;
}
