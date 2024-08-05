package com.resource.resource.mongo.repo;

import org.springframework.data.mongodb.core.mapping.Document;

import com.resource.resource.dto.Resource;


@Document(collection = "resource")
public class ResourceInclude{
	String id; /** Aqui colocaremos um id para controlar melhor os testes **/ 
	String operationType;
	String resourceType;		
	String resourceName;		
	String resourceNick;		
	String eat;
	String summary;	
	String image;
	String country;		
	String state;	
	String city;
	public ResourceInclude(Resource r){
		this.id = r.id();
		this.operationType = r.operationType();
		this.resourceType = r.resourceType();		
		this.resourceName = r.resourceName();		
		this.resourceNick = r.resourceNick();		
		this.eat = r.eat();
		this.summary = r.summary();
		this.image = r.image();
		this.country = r.country();		
		this.state = r.state();	
		this.city = r.city();
	}
}
