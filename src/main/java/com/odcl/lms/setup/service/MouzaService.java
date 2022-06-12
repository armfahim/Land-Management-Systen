package com.odcl.lms.setup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odcl.lms.setup.model.Mouza;
import com.odcl.lms.setup.repository.MouzaRepository;

@Service
public class MouzaService {

	@Autowired
	MouzaRepository mouzaRepository;

	public List<Mouza> getAllMouza() {
		return mouzaRepository.findAll();
	}

	public Mouza addMouza(@Valid Mouza mouza) {
		return mouzaRepository.save(mouza);
	}

}
