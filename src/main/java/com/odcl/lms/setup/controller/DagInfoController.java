package com.odcl.lms.setup.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.setup.model.DagInformation;
import com.odcl.lms.setup.service.DagInfoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/dag-info")
public class DagInfoController {

	@Autowired
	DagInfoService dagInfoService;

	@GetMapping("/get-all")
	public List<DagInformation> getAll() {
		return dagInfoService.getAllDagInfo();
	}

	@PostMapping("/add")
	public DagInformation add(@Valid @RequestBody DagInformation dagInformation) {
		return dagInfoService.addDagInfo(dagInformation);
	}

	@GetMapping("/find-by-id/{id}")
	public Optional<DagInformation> getById(@PathVariable(name = "id") Long id) {
		return dagInfoService.getDagInfoById(id);
	}

}
