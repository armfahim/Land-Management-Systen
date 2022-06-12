package com.odcl.lms.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Thana;
import com.odcl.lms.setup.repository.DistrictRepository;
import com.odcl.lms.setup.repository.ThanaRepository;

@Service
public class ThanaService {

	@Autowired
	ThanaRepository thanaRepository;

	@Autowired
	DistrictRepository districtRepository;

	public List<Thana> getAllThanas() {
		return thanaRepository.findAll();
	}

	public List<Thana> getThanasByDisId(Long id) {
		District district = districtRepository.findById(id).get();
		return thanaRepository.findAllByDistrict(district);
	}
}
