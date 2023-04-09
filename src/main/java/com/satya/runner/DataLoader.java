package com.satya.runner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.satya.entity.CitizenPlan;
import com.satya.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private CitizenPlanRepository citizenPlanRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		CitizenPlan c1 = new CitizenPlan();
		c1.setCitizenName("Mahesh Jhaa");
		c1.setGender("Male");
		c1.setPlanName("Medical");
		c1.setPlanStatus("Approved");
		c1.setPlanStartDate(LocalDate.now().minusMonths(8));
		c1.setPlanEndDate(LocalDate.now().minusMonths(2));
		c1.setBenifitAmt(5000.00);

		CitizenPlan c2 = new CitizenPlan();
		c2.setCitizenName("Prakash Sahoo");
		c2.setGender("Male");
		c2.setPlanName("Food");
		c2.setPlanStatus("Denied");
		c2.setDenialReason("Govt Employee");

		CitizenPlan c3 = new CitizenPlan();
		c3.setCitizenName("Manoj PRadhan");
		c3.setGender("Male");
		c3.setPlanName("Cash");
		c3.setPlanStatus("Terminated");
		c3.setPlanStartDate(LocalDate.now().minusMonths(4));
		c3.setPlanEndDate(LocalDate.now().plusMonths(2));
		c3.setTerminationDate(LocalDate.now());
		c3.setTerminationReason("Temporary Income");

		CitizenPlan c4 = new CitizenPlan();
		c4.setCitizenName("Gita Mishra");
		c4.setGender("Female");
		c4.setPlanName("Employment");
		c4.setPlanStatus("Approved");
		c4.setPlanStartDate(LocalDate.now());
		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
		c4.setBenifitAmt(10000.00);

		CitizenPlan c5 = new CitizenPlan();
		c5.setCitizenName("Niharika Jain");
		c5.setGender("Female");
		c5.setPlanName("Cash");
		c5.setPlanStatus("Denied");
		c5.setDenialReason("Large Amount Of Property");

		CitizenPlan c6 = new CitizenPlan();
		c6.setCitizenName("Deepika Mohapatra");
		c6.setGender("Female");
		c6.setPlanName("Food");
		c6.setPlanStatus("Terminated");
		c6.setPlanStartDate(LocalDate.now().minusMonths(1));
		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
		c6.setTerminationDate(LocalDate.now().minusDays(10));
		c6.setTerminationReason("Incorrect Documents");

		CitizenPlan c7 = new CitizenPlan();
		c7.setCitizenName("Nitin Kar");
		c7.setGender("Male");
		c7.setPlanName("Employment");
		c7.setPlanStatus("Approved");
		c7.setPlanStartDate(LocalDate.now().minusMonths(2));
		c7.setPlanEndDate(LocalDate.now().minusMonths(8));
		c7.setBenifitAmt(15000.00);

		CitizenPlan c8 = new CitizenPlan();
		c8.setCitizenName("Binita jadav");
		c8.setGender("Female");
		c8.setPlanName("Medical");
		c8.setPlanStatus("Denied");
		c8.setDenialReason("Govt Employee");

		CitizenPlan c9 = new CitizenPlan();
		c9.setCitizenName("Satyendra Jain");
		c9.setGender("Male");
		c9.setPlanName("Food");
		c9.setPlanStatus("Terminated");
		c9.setPlanStartDate(LocalDate.now().minusMonths(6));
		c9.setPlanEndDate(LocalDate.now().plusMonths(1));
		c9.setTerminationDate(LocalDate.now().minusDays(13));
		c9.setTerminationReason("False Documents");

		List<CitizenPlan> citizenList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9);
		this.citizenPlanRepo.deleteAll();
		this.citizenPlanRepo.saveAll(citizenList);
	}

}
