package com.odcl.lms.purchase.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.odcl.lms.common.utils.Response;
import com.odcl.lms.purchase.enumeration.SaleMedia;
import com.odcl.lms.purchase.model.Requisition;
import com.odcl.lms.purchase.service.RequisitionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/purchase/requisition")
public class RequisitionController {

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	RequisitionService requisitionService;

	@PostMapping("create")
	public ResponseEntity<Object> createRequisition(@Valid @RequestBody Requisition requisition) {
		return ResponseEntity.ok(requisitionService.create(requisition));
	}

	@GetMapping("/get-all")
	public Response getAll(HttpServletRequest request) {
		return requisitionService.gridList(request);
	}

	@GetMapping("/find-latest-requisition")
	public ResponseEntity<Object> latestRequisition() {
		return ResponseEntity.ok(requisitionService.findLatestRequisition());
	}

	@GetMapping("/find-by-id")
	public ResponseEntity<Object> findById(@RequestParam Long id) {
		return ResponseEntity.ok(requisitionService.findById(id));
	}

	@GetMapping("/fetch-sale-media")
	public ResponseEntity<Object> fetchSaleMedia() {
		return ResponseEntity.ok(Arrays.asList(SaleMedia.class.getEnumConstants()));
	}

	@PutMapping("/update") 
	public ResponseEntity<Object> update(@Valid @RequestBody Requisition requisition) {
		return ResponseEntity.ok(requisitionService.update(requisition));
	}
	
	@DeleteMapping("/delete-by-id") 
	public ResponseEntity<Object> deleteById(@RequestParam Long id) {
		return ResponseEntity.ok(requisitionService.deleteById(id));
	}
}
