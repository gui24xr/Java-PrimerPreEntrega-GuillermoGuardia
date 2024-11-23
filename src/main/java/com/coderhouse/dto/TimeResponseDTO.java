package com.coderhouse.dto;
import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Getter;
//import lombok.Setter;

//@Getter
//@Setter
@Schema(description = "Modelo de horario devuelvo por el api.")
public class TimeResponseDTO {

	private String currentDateTime;
	private long currentFileTime;
	
	
	public String getCurrentDateTime() {
		return currentDateTime;
	}
	public void setCurrentDateTime(String currentDateTime) {
		this.currentDateTime = currentDateTime;
	}
	public long getCurrentFileTime() {
		return currentFileTime;
	}
	public void setCurrentFileTime(long currentFileTime) {
		this.currentFileTime = currentFileTime;
	}
	
	
	
	}

