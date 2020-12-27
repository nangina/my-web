package com.example.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class MyService {

	private final MyRepository repo ;
	
	public MyService (MyRepository repo) {
		
		this.repo = repo;
	}
	
	
	public TestItem getItem(int id) throws IOException {
		
				return repo.getItem(id);
	}
	
	
	public List<TestItem> getItems() throws IOException {
		
		return repo.getItems();
}

}
