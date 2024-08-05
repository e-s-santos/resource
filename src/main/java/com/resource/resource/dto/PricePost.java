package com.resource.resource.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PricePost (
		@NotNull
		@NotEmpty
		String id, 
		@NotNull
		@NotEmpty
		float price
		) {

}
