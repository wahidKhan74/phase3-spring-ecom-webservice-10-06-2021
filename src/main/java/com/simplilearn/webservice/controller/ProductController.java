package com.simplilearn.webservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simplilearn.webservice.entity.Product;
import com.simplilearn.webservice.repository.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository pRepo;

	// list all products
	@GetMapping("/products")
	public List<Product> getProducts() {
		return pRepo.findAll();
	}

	// get on product
	@GetMapping("/products/{id}")
	public Product getProducts(@PathVariable("id") long id) {
		return pRepo.findById(id).get();
	}

	// create product
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product productObj) {
		return pRepo.save(productObj);
	}

	// update product
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product productObj) {
		// step 1: find product
		Product fetchedProduct = pRepo.findById(productObj.getId()).get();
		if (fetchedProduct != null) {
			// step 2: Map updating fields
			fetchedProduct.setName(productObj.getName());
			fetchedProduct.setPrice(productObj.getPrice());
			fetchedProduct.setDescription(productObj.getDescription());
			// step 3: update
			return pRepo.save(fetchedProduct);
		}
		return null;
	}

	// get on product
	@DeleteMapping("/products/{id}")
	public Product deleteProduct(@PathVariable("id") long id) {
		// step 1: find product
		Product fetchedProduct = pRepo.findById(id).get();
		if (fetchedProduct != null) {
			// step 2: delete
			pRepo.delete(fetchedProduct);
		}
		return null;
	}
}
