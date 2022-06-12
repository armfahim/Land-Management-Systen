package com.odcl.lms.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.District;
import com.odcl.lms.setup.service.DistrictService;

@CrossOrigin("*")
@RestController
@RequestMapping("/district")
public class DistrictController {

	@Autowired
	DistrictService districtService;

	@GetMapping("/get-all")
	public List<District> getAll() {
		return districtService.getAllDistricts();
	}

	@GetMapping("/find-by-id/{id}")
	public List<District> getById(@PathVariable(name = "id") Long id) {
		return districtService.getDistrictsByDivId(id);
	}

}
