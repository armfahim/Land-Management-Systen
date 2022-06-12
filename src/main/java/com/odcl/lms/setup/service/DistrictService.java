package com.odcl.lms.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.repository.DistrictRepository;
import com.odcl.lms.setup.repository.DivisionRepository;

@Service
public class DistrictService {

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	DivisionRepository divisionRepository;

	public List<District> getAllDistricts() {
		return districtRepository.findAll();
	}

	public List<District> getDistrictsByDivId(Long id) {
		Division division = divisionRepository.findById(id).get();
		return districtRepository.findAllByDivision(division);
	}

}
