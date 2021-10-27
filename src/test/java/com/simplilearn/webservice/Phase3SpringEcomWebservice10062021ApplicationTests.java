package com.simplilearn.webservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.simplilearn.webservice.controller.ProductController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@DisplayName("Application Test : Main Test")
class Phase3SpringEcomWebservice10062021ApplicationTests {

	@Autowired
	private ProductController controller;
	
	@LocalServerPort
	private int randomPort;
	
	@Autowired
	private TestRestTemplate template;
	
	
	@Test
	@DisplayName("Test : Load Application Context")
	void contextLoads() {
		assertNotNull(controller);
	}
	
	@Test
	@DisplayName("Test : Server Running Test")
	void testForRunningServer() {
		String url ="http://localhost:"+randomPort+"/";
		ResponseEntity<String> response = template.getForEntity(url, String.class);
		assertEquals("Server is up & running !", response.getBody());
		assertEquals(200, response.getStatusCodeValue());
	}

}
