package com.cubes.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cubes.model.Product;
import com.cubes.model.ProductLine;
import com.cubes.model.ext.ProductTemp;
import com.cubes.repository.ProductLineRepository;
import com.cubes.repository.ProductRepository;
import com.cubes.util.Header;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController

@RequestMapping("/product")
public class ProductController {
	@Autowired
	ProductRepository repo;
	@Autowired
	ProductLineRepository repoLine;
	@Autowired
	DiscoveryClient discoveryClient;

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
	@HystrixCommand(fallbackMethod = "failureResponseCreate", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public ResponseEntity<String> create(@RequestBody List<ProductTemp> line) {
		Function<Product, Product> func2 = x -> {

			java.util.Optional<ProductLine> prodLine = repoLine.findById(x.getProductLineID().getProductLineID());
			x.setProductLineID(prodLine.get());
			;
			return x;
		};

		Function<ProductTemp, Product> func = x -> {
			List<ServiceInstance> list = discoveryClient.getInstances("STOCK");
			ServiceInstance service2 = list.get(0);
			URI micro2URI = service2.getUri();
			Long micro2Response = new RestTemplate().postForObject(micro2URI + "/stock/stock/create", x.getStock(),
					Long.class);
			Product p = new Product();
			p.setCreatedAt(new Date());
			p.setProductName(x.getProductName());
			p.setProductDesc(x.getProductDesc());
			p.setPriceID(x.getPriceID());
			p.setVendorID(x.getVendorID());
			p.setProductLineID(x.getProductLineID());

			p.setStockID(micro2Response);

			return p;
		};

		List<Product> lineUpdated = line.stream().map(func).collect(Collectors.toList());

		List<Product> lineUpdated2 = lineUpdated.stream().map(func2).collect(Collectors.toList());

		lineUpdated2.stream().forEach(x -> repo.saveAndFlush(x));

		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody Product line) {
		repo.saveAndFlush(line);
		return new ResponseEntity<>("Action done ", Header.getHeader(), HttpStatus.OK);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<String> failureResponseCreate() {
		return new ResponseEntity<>("Action Failed ", Header.getHeader(), HttpStatus.EXPECTATION_FAILED);

	}

}
