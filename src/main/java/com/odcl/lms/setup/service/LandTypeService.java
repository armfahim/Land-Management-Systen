package com.odcl.lms.setup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.LandType;
import com.odcl.lms.setup.repository.LandTypeRepository;

@Service
public class LandTypeService {

	@Autowired
	LandTypeRepository landTypeRepository;

	public List<LandType> getAllLandType() {
		return landTypeRepository.findAll();
	}

	public LandType addLandType(@Valid LandType landType) {
		return landTypeRepository.save(landType);
	}

}
