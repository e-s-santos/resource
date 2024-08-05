package com.resource.resource.mongo.repo;

import org.springframework.data.mongodb.core.mapping.Document;
import com.resource.resource.dto.NewsPost;


@Document(collection = "news")
public class NewsPostMongo{
	String collection;
	String Id;
	String text;
	public NewsPostMongo(NewsPost np){
		this.collection = np.collection();
		this.Id = np.Id();
		this.text = np.text();		
	}
}
