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

import com.cubes.model.Price;
import com.cubes.repo.PriceRepository;
import com.cubes.util.Header;

@RestController

@RequestMapping("/price")
public class PriceController {

	@Autowired
	PriceRepository repo;

	@GetMapping("/fetchByID")
	public ResponseEntity<Price> fetchByID(@RequestBody Price line) {
		Optional<Price> pLine = repo.findById(line.getPriceId());
		return new ResponseEntity<>(pLine.get(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Price line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>(line.getPriceId(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Price line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

}
