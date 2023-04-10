package com.satya.service;

import java.util.List;

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

}
