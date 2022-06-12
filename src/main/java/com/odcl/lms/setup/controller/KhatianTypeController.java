package com.odcl.lms.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.KhatianType;
import com.odcl.lms.setup.service.KhatianTypeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/khatian-type")
public class KhatianTypeController {

	@Autowired
	KhatianTypeService khatianTypeService;

	@GetMapping("/get-all")
	public List<KhatianType> getAll() {
		return khatianTypeService.getAllKhatianType();
	}

}
