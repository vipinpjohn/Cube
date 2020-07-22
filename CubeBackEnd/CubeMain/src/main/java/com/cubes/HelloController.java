package com.cubes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cubes.model.ProductCatalogue;

@RestController
public class HelloController {

	@RequestMapping("/hello")
	public ResponseEntity<String> index() {
		HttpHeaders headers = new HttpHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Expose-Headers", "Authorization");

		return new ResponseEntity<>("Hello World!", headers, HttpStatus.OK);
	}

	@RequestMapping("/product")
	public ResponseEntity<List<ProductCatalogue>> product() {
		HttpHeaders headers = new HttpHeaders();

		// headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		headers.add("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With,observe");
		headers.add("Access-Control-Max-Age", "3600");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Expose-Headers", "Authorization");
		List<ProductCatalogue> prod = new ArrayList<ProductCatalogue>();
		ProductCatalogue p = new ProductCatalogue();
		p.setDesc("Desc");
		p.setId("2");
		p.setName("Name");

		prod.add(p);
		return new ResponseEntity<List<ProductCatalogue>>(prod, headers, HttpStatus.OK);
	}

}