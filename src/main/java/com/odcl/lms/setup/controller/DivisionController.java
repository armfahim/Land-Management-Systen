package com.odcl.lms.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.Division;
import com.odcl.lms.setup.service.DivisionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/division")
public class DivisionController {

	@Autowired
	DivisionService divisionService;

	@GetMapping("/get-all")
	public List<Division> getAllDivisions() {
		return divisionService.getAllDivisions();
	}

}
