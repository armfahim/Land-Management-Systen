package com.odcl.lms.setup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.LandType;
import com.odcl.lms.setup.service.LandTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/land-type")
public class LandTypeController {

	@Autowired
	LandTypeService landTypeService;

	@PostMapping("/add")
	public LandType add(@Valid @RequestBody LandType landType) {
		return landTypeService.addLandType(landType);
	}

	@GetMapping("/get-all")
	public List<LandType> getAll() {
		return landTypeService.getAllLandType();
	}

}
