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

import com.cubes.model.Stock;
import com.cubes.repo.StockRepository;
import com.cubes.util.Header;

@RestController

@RequestMapping("/stock")
public class StockController {

	@Autowired
	StockRepository repo;

	@GetMapping("/fetchByID")
	public ResponseEntity<Stock> fetchByID(@RequestBody Stock line) {
		Optional<Stock> pLine = repo.findById(line.getStockId());
		return new ResponseEntity<>(pLine.get(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Stock line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>(line.getStockId(), Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Stock line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

}
