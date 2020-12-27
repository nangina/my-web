package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestItem {
	
	@JsonProperty("Id")
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("name")
	private String name;
	
	

}
