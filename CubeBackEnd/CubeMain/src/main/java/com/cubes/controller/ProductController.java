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

import com.cubes.model.Product;
import com.cubes.repository.ProductRepository;
import com.cubes.util.Header;

@RestController

@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductRepository repo;

	@GetMapping("/fetchAll")
	public ResponseEntity<List<Product>> fetchAll() {
		List<Product> pLine = repo.findAll();
		return new ResponseEntity<>(pLine, Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/fetchByName")
	public ResponseEntity<List<Product>> fetchByName(@RequestBody String name) {
		List<Product> pLine = repo.findAll();
		List<Product> pLineFil = pLine.stream().filter(p -> p.getProductName().contains(name))
				.collect(Collectors.toList());
		return new ResponseEntity<>(pLineFil, Header.getHeader(), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestBody Product line) {

		repo.delete(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody List<Product> line) {
		Function<Product, Product> func = x -> {
			x.setCreatedAt(new Date());
			x.setUpdatedAt(new Date());
			return x;
		};
		List<Product> lineNew = line.stream().map(func).collect(Collectors.toList());
		lineNew.stream().forEach(x -> repo.saveAndFlush(x));

		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Product line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

}
