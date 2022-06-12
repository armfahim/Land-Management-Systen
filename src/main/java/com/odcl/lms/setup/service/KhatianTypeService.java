package com.odcl.lms.setup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.KhatianType;
import com.odcl.lms.setup.repository.KhatianTypeRepository;

@Service
public class KhatianTypeService {

	@Autowired
	KhatianTypeRepository khatianTypeRepository;

	public List<KhatianType> getAllKhatianType() {
		return khatianTypeRepository.findAll();
	}
}
