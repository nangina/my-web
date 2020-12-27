package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
public class MyController {
	
	private final MyService service;
	
	public MyController (MyService service) {
		this.service =service;
	}
	
	@GetMapping(value = "/getItem", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getItem(@RequestHeader ("id") String id) throws IOException {
		
		TestItem  testItem = null;
		
		testItem = service.getItem (Integer.parseInt(id));
		
		return ResponseEntity.ok(testItem);
				
	}
	
	@GetMapping(value = "/getItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TestItem>> getItems(@RequestHeader ("id") String id) throws IOException {
		
		List<TestItem>  items = null;
		
		items = service.getItems();
		
		return ResponseEntity.ok(items);
				
	}

}
