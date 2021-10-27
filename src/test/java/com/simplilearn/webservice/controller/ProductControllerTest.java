package com.simplilearn.webservice.controller;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.entity.Product;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Product Controller Test")
public class ProductControllerTest {

	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private TestRestTemplate template;
	
	@Test
	@DisplayName("Test :: List all products")
	public void testGetAllProducts() {
		String url ="http://localhost:"+randomPort+"/api/products";
		// Get all products
		ResponseEntity<List> response = template.getForEntity(url, List.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(false, response.getBody().isEmpty());
	}
	
	
	@Test
	@DisplayName("Test :: Get one product")
	public void testGetOneProduct() {
		String url ="http://localhost:"+randomPort+"/api/products/12";
		// Get all products
		ResponseEntity<Product> response = template.getForEntity(url, Product.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals(12, response.getBody().getId());
		assertEquals("MacBook Pro - Space Grey", response.getBody().getName());
//		System.out.println(response.getBody());
		
	}
	
	@Test
	@DisplayName("Test :: Get one product which does not exist")
	public void testGetOneProductNotExist() {
		String url ="http://localhost:"+randomPort+"/api/products/1200";
		// Get all products
		ResponseEntity<Product> response = template.getForEntity(url, Product.class);
		assertEquals(404, response.getStatusCodeValue());
	}
	
	
	@Test
	@DisplayName("Test :: Add one product")
	public void testAddOneProduct() {
		String url ="http://localhost:"+randomPort+"/api/products";
		//create product object
		Product product = new Product("Lenovo Laptop xyz series", "It is a good laptop", 4564.56);
		//Http request entity object
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		//add one products
		ResponseEntity<Product> response = template.postForEntity(url, requestObj,Product.class);
		
		assertEquals(200, response.getStatusCodeValue());
		assertNotNull(response.getBody());
		assertEquals("Lenovo Laptop xyz series", response.getBody().getName());
		
	}
	
	@Test
	@DisplayName("Test :: Add one product with null value")
	public void testAddOneNullProduct() {
		String url ="http://localhost:"+randomPort+"/api/products";
		//create product object
		Product product = null;
		//Http request entity object
		HttpEntity<Product> requestObj = new HttpEntity<>(product);
		
		//add one products
		ResponseEntity<Product> response = template.postForEntity(url, requestObj,Product.class);
		
		assertEquals(415, response.getStatusCodeValue());
		
	}
	
	// TODO : WAUT For update and delete products
	
}
