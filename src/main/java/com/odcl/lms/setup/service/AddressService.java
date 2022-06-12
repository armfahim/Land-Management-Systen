package com.odcl.lms.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.model.KhatianType;
import com.odcl.lms.setup.model.LandType;
import com.odcl.lms.setup.model.Mouza;
import com.odcl.lms.setup.model.Thana;
import com.odcl.lms.setup.repository.DistrictRepository;
import com.odcl.lms.setup.repository.DivisionRepository;
import com.odcl.lms.setup.repository.KhatianTypeRepository;
import com.odcl.lms.setup.repository.LandTypeRepository;
import com.odcl.lms.setup.repository.MouzaRepository;
import com.odcl.lms.setup.repository.ThanaRepository;

@Service
public class AddressService {

	@Autowired
	DivisionRepository divisionRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	ThanaRepository thanaRepository;

	@Autowired
	KhatianTypeRepository khatianTypeRepository;

	@Autowired
	MouzaRepository mouzaRepository;
	
	@Autowired
	LandTypeRepository landTypeRepository;

	public List<Division> getAllDivisions() {
		return divisionRepository.findAll();
	}

	public List<District> getAllDistricts() {
		return districtRepository.findAll();
	}

	public List<Thana> getAllThanas() {
		return thanaRepository.findAll();
	}

	public List<District> getDistrictsByDivId(Long id) {
		Division division = divisionRepository.findById(id).get();
		return districtRepository.findAllByDivision(division);
	}

	public List<Thana> getThanasByDisId(Long id) {
		District district = districtRepository.findById(id).get();
		return thanaRepository.findAllByDistrict(district);
	}

	public List<KhatianType> getAllKhatianType() {
		return khatianTypeRepository.findAll();
	}

	public List<Mouza> getAllMouza() {
		return mouzaRepository.findAll();
	}
	
	public List<LandType> getAllLandType() {
		return landTypeRepository.findAll();
	}
}
