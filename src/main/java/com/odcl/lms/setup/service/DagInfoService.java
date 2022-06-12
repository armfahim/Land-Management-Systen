package com.odcl.lms.setup.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.DagInformation;
import com.odcl.lms.setup.repository.DagInfoRepository;

@Service
public class DagInfoService {

	@Autowired
	DagInfoRepository dagInfoRepository;

	public List<DagInformation> getAllDagInfo() {
		return dagInfoRepository.findAll();
	}

	public DagInformation addDagInfo(@Valid DagInformation dagInformation) {
		return dagInfoRepository.save(dagInformation);
	}

	public Optional<DagInformation> getDagInfoById(Long id) {
		
		return dagInfoRepository.findById(id);
	}

}
