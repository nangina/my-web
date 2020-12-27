package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
public class DynamoDBConfig {
			
	public DynamoDBConfig() {
		
	}
	
	@Bean
	public DynamoDB ddb (AmazonDynamoDB adb) {
		
    	DynamoDB ddb = new DynamoDB(adb);
    	
    	return ddb;		
	}
	
	@Bean
	public AmazonDynamoDB adb () {
		
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		return client;
		
	}
	
	@Bean
	public DynamoDBMapper dbm (AmazonDynamoDB adb) throws InterruptedException {
		
		DynamoDBMapperConfig.Builder builder = new DynamoDBMapperConfig.Builder();
		
		DynamoDBMapperConfig config = builder.build();
		
		return new DynamoDBMapper(adb, config);
		
		
	}
	

}
