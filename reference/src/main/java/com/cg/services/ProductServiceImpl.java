package com.cg.services;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.daos.ProductDAO;
import com.cg.entities.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO dao;
	
	@Transactional(readOnly = true)
	public Product find(Integer productId) {
		Optional<Product> product = dao.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}else
			throw new RuntimeException("Product not found");
	}

	@Transactional(readOnly = true)
	public List<Product> getAll() {
		return dao.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void create(Product product) {
		if(dao.existsById(product.getId())) {
			throw new RuntimeException("Product Already exist");
		}
		dao.saveAndFlush(product);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean update(Product p) {
		Optional<Product> temp = dao.findById(p.getId());
		if(temp.isPresent()) {
			Product pro = temp.get();
			pro.setName(p.getName());
			dao.saveAndFlush(pro);
			return true;
		}else {
			throw new RuntimeException("Product not found");
		}
	}

}
