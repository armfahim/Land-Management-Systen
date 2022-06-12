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

import com.odcl.lms.setup.model.Mouza;
import com.odcl.lms.setup.service.MouzaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/mouza")
public class MouzaController {

	@Autowired
	MouzaService mouzaService;

	@PostMapping("/add")
	public Mouza add(@Valid @RequestBody Mouza mouza) {
		return mouzaService.addMouza(mouza);
	}

	@GetMapping("get-all")
	public List<Mouza> getAll() {
		return mouzaService.getAllMouza();
	}

}
