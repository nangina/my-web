package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.xspec.ExpressionSpecBuilder;
import com.amazonaws.services.dynamodbv2.xspec.ScanExpressionSpec;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class MyRepository {

	private final DynamoDB ddb;
	//private final AmazonDynamoDB addb;
	private final ObjectMapper mapper = new ObjectMapper();
	
		public MyRepository(DynamoDB ddb)throws IOException {
			
			this.ddb = ddb;
			//this.mapper = dbm;
		
/*
		public MyRepository (DynamoDB ddb, ObjectMapper obm, AmazonDynamoDB addb) throws IOException {
		
		this.ddb = ddb;
		this.obm = obm;
		this.addb = addb;
		
	}
*/
		}
		
	
	public TestItem getItem(int id) throws IOException {
	
		TestItem tItem = new TestItem();
		Item item = null;
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		//AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    	//DynamoDB ddb = new DynamoDB(client);
		
		Table table = ddb.getTable("test");
		
		ScanExpressionSpec sesb = new ExpressionSpecBuilder()
				.withCondition(ExpressionSpecBuilder.N("Id").eq(id)).buildForScan();
		ItemCollection<ScanOutcome> items = table.scan(sesb);
		Iterator<Item> itr = items.iterator();
		
		while (itr.hasNext()) {
			item = itr.next();
			//tItem = obm.readValue(item.toJSONPretty(), TestItem.class);
			System.out.println("Test Item: " + item.toJSONPretty());
		
			System.out.println("ID: " + id);
			//tItem.setId(id);
			//tItem.setName(item.getString("name"));
			
		}
		
		//return tItem;
		
		return mapper.readValue(item.toJSONPretty(), TestItem.class);
	}
	
	
	public List<TestItem> getItems() throws IOException {
	
		TestItem tItem = new TestItem();
		Item item = null;
		
		List<TestItem>  tItems = new ArrayList();
		
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		Table table = ddb.getTable("test");
		
		ScanExpressionSpec sesb = new ExpressionSpecBuilder().buildForScan();
	
		ItemCollection<ScanOutcome> items = table.scan(sesb);
		Iterator<Item> itr = items.iterator();
	
		while (itr.hasNext()) {
			item = itr.next();		
			System.out.println("Test Item: " + item.toJSONPretty());
			tItems.add(mapper.readValue(item.toJSONPretty(), TestItem.class));
		}
				
		return tItems;
	}


	

}
