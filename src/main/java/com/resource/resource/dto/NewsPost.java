package com.resource.resource.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record NewsPost (
		@NotNull
		@NotEmpty
		String collection, 
		@NotNull
		@NotEmpty
		String Id,
		@NotNull
		@NotEmpty
		String text
		) {
}
