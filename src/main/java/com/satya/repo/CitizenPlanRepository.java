package com.satya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.satya.entity.CitizenPlan;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan, Integer>{
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlans();
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();
//	public List<CitizenPlan> findByPlanName(String planName);
//	public List<CitizenPlan> findByPlanStatus(String planStatus);
//	public List<CitizenPlan> findByGender(String gender);
//	public List<CitizenPlan> findByPlanStartDate(LocalDate date);
//	public List<CitizenPlan> findByPlanEndDate(LocalDate date);
}
