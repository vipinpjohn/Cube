package com.cubes.controller;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubes.model.ProductLine;
import com.cubes.repository.ProductLineRepository;
import com.cubes.util.Header;

@RestController

@RequestMapping("/productLine")
public class ProductLineController {
	@Autowired
	ProductLineRepository repo;

	@GetMapping("/fetchAll")
	public ResponseEntity<List<ProductLine>> fetchAll() {
		List<ProductLine> pLine = repo.findAll();
		return new ResponseEntity<>(pLine, Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/fetchByName")
	public ResponseEntity<List<ProductLine>> fetchByName(@RequestBody String line) {
		List<ProductLine> pLine = repo.findAll();
		List<ProductLine> pLineFil = pLine.stream().filter(p -> p.getProductLineName().contains(line))
				.collect(Collectors.toList());
		return new ResponseEntity<>(pLineFil, Header.getHeader(), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody ProductLine line) {

		repo.delete(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody List<ProductLine> line) {
		Function<ProductLine, ProductLine> func = x -> {
			x.setCreatedAt(new Date());
			x.setUpdatedAt(new Date());
			return x;
		};
		List<ProductLine> lineNew = line.stream().map(func).collect(Collectors.toList());
		lineNew.stream().forEach(x -> repo.saveAndFlush(x));

		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody ProductLine line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

}
