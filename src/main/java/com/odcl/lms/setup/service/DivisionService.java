package com.odcl.lms.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.repository.DivisionRepository;

@Service
public class DivisionService {

	@Autowired
	DivisionRepository divisionRepository;

	public List<Division> getAllDivisions() {
		return divisionRepository.findAll();
	}
}
