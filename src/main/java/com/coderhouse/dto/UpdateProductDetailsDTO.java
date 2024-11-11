package com.coderhouse.dto;

import jakarta.validation.constraints.NotNull;

public class UpdateProductDetailsDTO {
	
	
	@NotNull
	private String code;
	@NotNull
	private String title;
	

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	} 
	
	
	
	
	
}
