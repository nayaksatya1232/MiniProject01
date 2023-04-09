package com.satya.service;

import java.util.List;

import com.satya.entity.CitizenPlan;
import com.satya.request.SearchRequest;

public interface ReportService {
	public List<String> getPlans();
	public List<String> getStatus();
	public List<CitizenPlan> search(SearchRequest request);
}
