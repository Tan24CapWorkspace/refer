package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entities.Product;
import com.cg.services.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value="/", produces = {"application/json","application/xml","text/html"})
	public String createSample() {
		return "Started";
	}
	
	@GetMapping(value="/list", produces = {"application/json","application/xml","text/html"})
	public List<Product> getAll(){
		return service.getAll();
	}
	
	@PostMapping(value="/add", consumes = {"application/json","application/xml","text/html"}, produces = {"application/json","application/xml","text/html"})
	public String add(@RequestBody Product product) {
		service.create(product);
		return "product added";
	}
	
	@PostMapping(value="/update", consumes = {"application/json","application/xml","text/html"}, produces = {"application/json","application/xml","text/html"})
	public String update(@RequestBody Product product) {
		service.update(product);
		return "updated";
	}
	
}
