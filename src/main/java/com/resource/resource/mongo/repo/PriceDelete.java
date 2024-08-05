package com.resource.resource.mongo.repo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "price")
public class PriceDelete {
	private  String id ;
	public PriceDelete(com.resource.resource.dto.PriceDelete pd){
		this.id = pd.id();
	}
}
