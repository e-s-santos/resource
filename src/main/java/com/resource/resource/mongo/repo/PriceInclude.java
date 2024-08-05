package com.resource.resource.mongo.repo;

import org.springframework.data.mongodb.core.mapping.Document;
import com.resource.resource.dto.NewsPost;
import com.resource.resource.dto.PricePost;


@Document(collection = "price")
public class PriceInclude{
	String id;
	float price;
	public PriceInclude(PricePost np){
		this.id = np.id();
		this.price = np.price();
	}
}
