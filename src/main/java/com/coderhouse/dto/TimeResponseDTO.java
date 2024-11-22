package com.coderhouse.dto;

//import lombok.Data;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
@Schema(description = "Modelo de horario devuelvo por el api.")
public class TimeResponseDTO {

	private String currentDateTime;

	
	public String getCurrentDateTime() {
		return currentDateTime;
	}

	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
		
	
	}

