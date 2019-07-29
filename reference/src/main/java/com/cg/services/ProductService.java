package com.cg.services;

import java.util.List;

import com.cg.entities.Product;

public interface ProductService {
	public Product find(Integer productId);
	public List<Product> getAll();
	void create(Product product);
	public boolean update(Product p);
}
