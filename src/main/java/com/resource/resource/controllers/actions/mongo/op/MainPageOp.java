package com.resource.resource.controllers.actions.mongo.op;

import java.util.List;

public record MainPageOp (
		String _id,
		String operationType,
		String resourceType,
		String resourceName,
		String resourceNick,
		String eat,
		String summary,
		String image,
		String country,
		String state,
		String city,
		List<ListText>  news//,
		//ListPrice price
		//String newsId,
		//String text
		)
{
	/*String _id;
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
	String newsId;
	String text;*/
	
}
