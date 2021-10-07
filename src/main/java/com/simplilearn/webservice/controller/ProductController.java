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
import com.simplilearn.webservice.exceptions.InvalidProductException;
import com.simplilearn.webservice.exceptions.ProductNotFoundException;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository pRepo;

	// list all products
	@GetMapping("/products")
	public List<Product> getProducts() {
		List<Product> list = pRepo.findAll();
		if (!list.isEmpty()) {
			return list;
		}
		throw new ProductNotFoundException("Product list is empty !");
	}

	// get on product
	@GetMapping("/products/{id}")
	public Product getProducts(@PathVariable("id") long id) {
		Product fetchedProduct = pRepo.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + id);
		});
		return fetchedProduct;
	}

	// create product
	@PostMapping("/products")
	public Product addProduct(@RequestBody(required=false) Product productObj) {
		if(productObj !=null) {
			return pRepo.save(productObj);
		}
		throw new InvalidProductException("Product creation failed ! missing project object !");
	}

	// update product
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product productObj) {
		// step 1: find product
		Product fetchedProduct = pRepo.findById(productObj.getId()).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + productObj.getId());
		});
		// step 2: Map updating fields
		fetchedProduct.setName(productObj.getName());
		fetchedProduct.setPrice(productObj.getPrice());
		fetchedProduct.setDescription(productObj.getDescription());
		// step 3: update
		return pRepo.save(fetchedProduct);

	}

	// get on product
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable("id") long id) {
		// step 1: find product
		Product fetchedProduct = pRepo.findById(id).orElseThrow(() -> {
			throw new ProductNotFoundException("Product does not exist with id " + id);
		});
		// step 2: delete
		pRepo.delete(fetchedProduct);
	}
}
