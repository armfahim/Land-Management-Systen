package com.odcl.lms.setup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.Thana;
import com.odcl.lms.setup.service.ThanaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/thana")
public class ThanaController {

	@Autowired
	ThanaService thanaService;

	@GetMapping("/get-all")
	public List<Thana> getAllThanas() {
		return thanaService.getAllThanas();
	}

	@GetMapping("/find-by-id/{id}")
	public List<Thana> getById(@PathVariable(name = "id") Long id) {
		return thanaService.getThanasByDisId(id);
	}

}
