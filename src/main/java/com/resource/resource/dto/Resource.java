package com.resource.resource.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Resource(
		@NotNull
		@NotEmpty
		String id ,
		@NotNull
		@NotEmpty
		String operationType,
		@NotNull
		@NotEmpty
		String resourceType,
		@NotNull
		@NotEmpty
		String resourceName,
		@NotNull
		@NotEmpty
		String resourceNick,
		@NotNull
		@NotEmpty
		String eat,
		@NotNull
		@NotEmpty
		String summary,
		@NotEmpty
		String image,
		@NotEmpty
		String country,
		@NotEmpty
		String state,
		@NotEmpty
		String city){
/*
 type:
 	*atualização por arquivo
 	*atualização por restapi
 locate: 
 	* localozização
 * */	
}
