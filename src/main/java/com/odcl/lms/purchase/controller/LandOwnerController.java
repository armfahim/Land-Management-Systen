package com.odcl.lms.purchase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.odcl.lms.purchase.model.LandOwner;
import com.odcl.lms.purchase.service.LandOwnerService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/land-owner")
public class LandOwnerController {

	@Autowired
	LandOwnerService landOwnerService;

	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody LandOwner reqObj) {
		return ResponseEntity.ok(landOwnerService.createLandOwner(reqObj));
	}

	@PutMapping("/update")
	public ResponseEntity<Object> edit(@Valid @RequestBody LandOwner reqObj) {
		return ResponseEntity.ok(landOwnerService.editLandOwner(reqObj));
	}

	@GetMapping("get-all")
	public List<LandOwner> getAllList(@RequestBody(required = false) String reqObj) {
		return landOwnerService.getAllListOfLandOwner(reqObj);
	}

	@GetMapping("/find-by-id")
	public ResponseEntity<LandOwner> getById(@RequestParam("id") Long id) {
		return landOwnerService.getLandOwnerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete")
	public void remove(@RequestParam("id") Long id) {
		landOwnerService.remove(id);
	}

	@GetMapping("grid-list")
	public Response gridList(HttpServletRequest request) {
		return landOwnerService.gridList(request);
	}

}
