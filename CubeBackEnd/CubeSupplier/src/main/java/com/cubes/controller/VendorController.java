package com.cubes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubes.model.Vendor;
import com.cubes.repo.VendorRepository;
import com.cubes.util.Header;

@RestController

@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	VendorRepository repo;

	@GetMapping("/fetchByID")
	public ResponseEntity<Vendor> fetchByID(@RequestBody Vendor line) {
		Optional<Vendor> pLine = repo.findById(line.getVendorId());
		return new ResponseEntity<>(pLine.get(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Vendor line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>(line.getVendorId(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Vendor line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

}
