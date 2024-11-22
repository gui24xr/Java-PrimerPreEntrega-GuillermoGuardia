package com.coderhouse.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.coderhouse.dto.TimeHourResponseDTO;



@Service
public class ApiTimeService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public TimeHourResponseDTO getCurrentDate() {
		try {
			final String URL = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
			System.out.println("En servicio API TIME" + restTemplate.getForObject(URL,String.class));
			//System.out.println("En servicio API TIME pero mapeado" + restTemplate.getForObject(URL,TimeHourResponseDTO.class));
			//return restTemplate.getForObject(URL, TimeHourResponseDTO.class);
			return new TimeHourResponseDTO();
			//return new TimeHourResponseDTO();
		}catch(RestClientException error) {
			System.err.println("Error, No se pudo conectar a la API Externa: " + error.getMessage());
			return null;
		}
		
	}

}
